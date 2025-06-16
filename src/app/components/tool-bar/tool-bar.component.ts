
import { Component } from '@angular/core';
import { UiService } from "../../service/shared/ui.service";

@Component({
  selector: 'app-tool-bar',
  templateUrl: './tool-bar.component.html',
  styleUrls: ['./tool-bar.component.css']
})
export class ToolBarComponent {

  constructor(private uiService: UiService) { }


  onToggleSidenav() {
    this.uiService.toggleSidenav();
  }
}
