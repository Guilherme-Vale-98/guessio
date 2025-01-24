import { Component, OnInit } from '@angular/core';
import { MatchInterface } from '../types/MatchInterface';
import { MatchService } from '../services/match.service';
import { CommonModule } from '@angular/common';
import {FormsModule } from '@angular/forms'
import { ErrorInterface } from '../types/ErrorInterface';

@Component({
  selector: 'app-match',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './match.component.html',
  styleUrl: './match.component.css'
})
export class MatchComponent implements OnInit {
  match!: MatchInterface; 
  searchQuery: string = '';
  errorMessage: ErrorInterface = {title:"", message: ""}; 

  constructor(private matchService: MatchService) {}

  ngOnInit(): void {
    this.matchService.getMatch().subscribe({
      next: (matchData) => {
        this.match = matchData;
      },
      error: (error) =>{
        this.errorMessage['message'] = error.message
        this.errorMessage['title'] = error.name
        console.log(this.errorMessage)
      }
    });
  }

  onSearch(): void {
    console.log(this.searchQuery);
  }

  onGuessClick(): void {
    if (!this.match) return;
  
    this.matchService.attemptAnswer(this.searchQuery, this.match.id).subscribe({
      next: (updatedMatch) => {
        this.match = updatedMatch;
        console.log(this.match);
      },
      error: (error) => {
        console.error('Error:', error);
      },
      complete: () => {
        console.log('Guess attempt complete.');
      },
    });


  }
}
