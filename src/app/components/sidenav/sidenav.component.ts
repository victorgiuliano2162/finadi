import { Component, ViewChild } from '@angular/core';
import { MatDrawer } from '@angular/material/sidenav';
import { Observable, Subscription } from 'rxjs';
import { UiService } from '../../service/shared/ui.service';
import { User } from 'src/app/models/usuario.model';
import { AuthService } from 'src/app/service/shared/auth.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css'],
})
export class SidenavComponent {
  currentUser$: Observable<User | null>;
  @ViewChild('drawer') private drawer!: MatDrawer;
  private drawerSubscription!: Subscription;
  cont = 0;
  constructor(private uiService: UiService, private authService: AuthService) {
    this.cont++;
    this.currentUser$ = this.authService.currentUser$;
  }

  public show() {
    if ((this.cont = 1)) {
      this.toggleDrawer();
    }
  }

  ngOnInit(): void {
    this.drawerSubscription = this.uiService.sidenavToggle$.subscribe(() => {
      this.toggleDrawer();
    });
    this.show();
  }

  toggleDrawer() {
    if (this.drawer) {
      this.drawer.toggle();
    }
  }

  ngOnDestroy(): void {
    if (this.drawerSubscription) {
      this.drawerSubscription.unsubscribe();
    }
  }

  logout() {
    this.authService.logout();
  }
}
