import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatchInterface } from '../types/MatchInterface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  constructor(private http: HttpClient) { }

  getMatch(): Observable<MatchInterface>{
    return this.http.get<MatchInterface>("http://localhost:8080/api/v1/match");
  }

  attemptAnswer(game: string, matchId: string): Observable<MatchInterface> {
    let gameTitleJson = JSON.parse(`{"gameTitle": "${game}"}`)
    return this.http.post<MatchInterface>(`http://localhost:8080/api/v1/matches/${matchId}`, gameTitleJson);
  }
  
  getGameNames(): Observable<string[]>{
    return this.http.get<string[]>("http://localhost:8080/api/v1/games")
  }
}
