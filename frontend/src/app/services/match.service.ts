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
}
