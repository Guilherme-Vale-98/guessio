import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { OptionBoxComponent } from "./option-box/option-box.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, OptionBoxComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit  {
  isRootPath = false;
  title = 'frontend';
  constructor(private router: Router) {}
  ngOnInit(): void {
      this.router.events.subscribe((event) =>{
        if(event instanceof NavigationEnd){
          this.isRootPath = event.url === '/';
        }
      })
  }
  
}
