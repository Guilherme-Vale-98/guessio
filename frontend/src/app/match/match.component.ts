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
      }
    });
  }

  onSearch(): void {
//    console.log(this.searchQuery);
  }

  getBackgroundColor(attemptArray: string[], answerArray: string[]){
    
    const containsAny = attemptArray.every(entry => !answerArray.includes(entry))
    if (containsAny) return "#D91515"
  
    const allMatch = attemptArray.every(entry => answerArray.includes(entry)) && answerArray.every(entry => attemptArray.includes(entry));
    if (allMatch) return 'green';

    return '#D7AB19';
  }
  onGuessClick(): void {
    if (!this.match) return;
    this.errorMessage = {title:"", message: ""};
    this.matchService.attemptAnswer(this.searchQuery, this.match.id).subscribe({
      next: (updatedMatch) => {
        this.match = updatedMatch;
        console.log(this.match);
      },
      error: (error) => {
        this.errorMessage['message'] = error.error;
        this.errorMessage['title'] = error.name;
      }
    });


  }
}
