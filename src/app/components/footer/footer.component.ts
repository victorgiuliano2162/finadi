import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {
  title = 'Fincon';
  currentYear: number;

  constructor() {
    this.currentYear = new Date().getFullYear();
  }
}
