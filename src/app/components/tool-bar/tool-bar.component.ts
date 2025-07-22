import { Component } from '@angular/core';
import { UiService } from '../../service/shared/ui.service';
import { AuthService } from 'src/app/service/shared/auth.service';
import { User } from 'src/app/models/usuario.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-tool-bar',
  templateUrl: './tool-bar.component.html',
  styleUrls: ['./tool-bar.component.css'],
})
export class ToolBarComponent {
  constructor(private uiService: UiService, private authService: AuthService) {
    this.currentUser$ = this.authService.currentUser$;
  }

  currentUser$: Observable<User | null>;

  onToggleSidenav() {
    this.uiService.toggleSidenav();
  }

  logout() {
    this.authService.logout();
  }
}
