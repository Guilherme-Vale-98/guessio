import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-option-box',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './option-box.component.html',
  styleUrl: './option-box.component.css'
})
export class OptionBoxComponent {
    @Input() title!: string
    @Input() description!: string
    @Input() url!: string

    constructor(private router: Router){}

    navigate(){
        if(this.url){
          this.router.navigateByUrl(this.url);
        }
    }
}
