import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatchInterface } from '../types/MatchInterface';
import { MatchService } from '../services/match.service';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-match',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './match.component.html',
  styleUrl: './match.component.css'
})
export class MatchComponent implements OnInit{
  match$!: Observable<MatchInterface>

  constructor(private matchService: MatchService){}

  ngOnInit(): void {
    this.match$ = this.matchService.getMatch();
  }
}
