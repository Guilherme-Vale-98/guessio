import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  constructor(private http: HttpClient) { }

  getMatch(){
    this.http.get("http://localhost:8080/api/v1/matches", {observe: "response"}).subscribe(
      res => {
        console.log('Response status:', res.status);  
        console.log('Body:', res.body);
      } 
    )
  }
}
