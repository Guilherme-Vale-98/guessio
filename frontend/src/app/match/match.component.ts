import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { MatchInterface } from '../types/MatchInterface';
import { MatchService } from '../services/match.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ErrorInterface } from '../types/ErrorInterface';

@Component({
  selector: 'app-match',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './match.component.html',
  styleUrl: './match.component.css',
})
export class MatchComponent implements OnInit {
  match!: MatchInterface;
  searchQuery: string = '';
  errorMessage: ErrorInterface = { title: '', message: '' };
  allGameNames: string[] = [];
  filteredGameNames: string[] = [];
  pixelationLevel: number = 50;
  rowNumber: number = 0;
  columnNumber: number = 1;
  hearts: string[] = Array(5).fill('/8bitHeart.png');

  @ViewChild('pixelCanvas', { static: true })
  pixelCanvas!: ElementRef<HTMLCanvasElement>;
  private ctx!: CanvasRenderingContext2D;
  private originalImage!: HTMLImageElement;

  constructor(private matchService: MatchService) {}

  ngOnInit(): void {
    this.matchService.getMatch().subscribe({
      next: (matchData) => {
        this.match = matchData;
        this.loadImage(matchData.answer.imageUrls[0]);
      },
      error: (error) => {
        this.errorMessage['message'] = error.message;
        this.errorMessage['title'] = error.name;
      },
    });

    this.matchService.getGameNames().subscribe({
      next: (gameNames) => {
        this.allGameNames = gameNames;
      },
      error: (error) => {
        this.errorMessage['message'] = error.message;
        this.errorMessage['title'] = error.name;
      },
    });
  }

  loadImage(imageUrl: string): void {
    this.originalImage = new Image();
    this.originalImage.src = imageUrl;
    this.originalImage.onload = () => {
      this.ctx = this.pixelCanvas.nativeElement.getContext('2d')!;
      this.renderPixelatedImage();
    };
  }

  updateHearts(): void {
    this.hearts[this.hearts.length - this.match.attempts.length] =
      '/emptyHeart.png';
  }
  revealImage(): void {
    const canvas = this.pixelCanvas.nativeElement;
    const ctx = this.ctx;

    const startX = (canvas.width * this.rowNumber) / 2;
    const startY = (canvas.height * this.columnNumber) / 3;
    const regionWidth = canvas.width / 2;
    const regionHeight = canvas.height / 3;

    const animateRegionPixelation = (currentPixelation: number) => {
      if (currentPixelation < 1) return;

      const scaledWidth = regionWidth / currentPixelation;
      const scaledHeight = regionHeight / currentPixelation;

      ctx.drawImage(
        this.originalImage,
        startX,
        startY,
        regionWidth,
        regionHeight,
        startX,
        startY,
        scaledWidth,
        scaledHeight
      );

      ctx.imageSmoothingEnabled = false;
      ctx.drawImage(
        canvas,
        startX,
        startY,
        scaledWidth,
        scaledHeight,
        startX,
        startY,
        regionWidth,
        regionHeight
      );

      setTimeout(() => animateRegionPixelation(currentPixelation - 1), 30);
    };

    animateRegionPixelation(this.pixelationLevel);

    this.rowNumber++;
    if (this.rowNumber % 2 === 0) {
      this.columnNumber++;
      this.rowNumber = 0;
    }
  }

  renderPixelatedImage(): void {
    const canvas = this.pixelCanvas.nativeElement;
    const ctx = this.ctx;
    const pixelSize = this.pixelationLevel;

    canvas.width = this.originalImage.width;
    canvas.height = this.originalImage.height;

    const scaledWidth = canvas.width / pixelSize;
    const scaledHeight = canvas.height / pixelSize;

    ctx.drawImage(this.originalImage, 0, 0, scaledWidth, scaledHeight);

    ctx.imageSmoothingEnabled = false;
    ctx.drawImage(
      canvas,
      0,
      0,
      scaledWidth,
      scaledHeight,
      0,
      0,
      canvas.width,
      canvas.height
    );

    ctx.drawImage(
      this.originalImage,
      0,
      0,
      canvas.width,
      canvas.height / 3,
      0,
      0,
      canvas.width,
      canvas.height / 3
    );
  }

  getBackgroundColor(attemptArray: string[], answerArray: string[]) {
    const containsAny = attemptArray.every(
      (entry) => !answerArray.includes(entry)
    );
    if (containsAny) return '#D91515';

    const allMatch =
      attemptArray.every((entry) => answerArray.includes(entry)) &&
      answerArray.every((entry) => attemptArray.includes(entry));
    if (allMatch) return 'green';

    return '#D7AB19';
  }

  onSearch(): void {
    this.filteredGameNames = this.allGameNames.filter((name) =>
      name.toLowerCase().includes(this.searchQuery.toLowerCase())
    );
  }

  onGuessClick(): void {
    if (!this.match || this.match.status === 'FINISHED') return;
    if (!this.filteredGameNames.includes(this.searchQuery)) return;

    this.errorMessage = { title: '', message: '' };
    this.matchService.attemptAnswer(this.searchQuery, this.match.id).subscribe({
      next: (updatedMatch) => {
        this.match = updatedMatch;
        this.revealImage();
        if (this.match.answer.name === this.searchQuery) {
          this.revealFullImage();
          return;
        }
        if (this.match.answer.name !== this.searchQuery) this.updateHearts();
      },
      error: (error) => {
        this.errorMessage['message'] = error.error;
        this.errorMessage['title'] = error.name;
      },
    });
  }
  revealFullImage(): void {
    this.revealImage();
    this.revealImage();
    this.revealImage();
    this.revealImage();
    this.revealImage();
    this.revealImage();
  }
  newMatch(): void {
    window.location.reload();
  }
}
