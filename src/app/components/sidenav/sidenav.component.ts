import {Component, ViewChild} from '@angular/core';
import {MatDrawer} from "@angular/material/sidenav";
import {Subscription} from "rxjs";
import {UiService} from "../../service/shared/ui.service";

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent {

  @ViewChild('drawer') private drawer!: MatDrawer;
  private drawerSubscription!: Subscription;
  cont = 0;
  constructor(private uiService: UiService) {
    this.cont++;
    console.log(this.cont)
  }


  public show() {
    if(this.cont = 1) {
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
    } else {
      console.error('MatSidenav not found. Make sure #snav is set correctly in the template.');
    }
  }

  ngOnDestroy(): void {
    if (this.drawerSubscription) {
      this.drawerSubscription.unsubscribe();
    }
  }

}
