import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UiService {
  private _sidenavToggle = new Subject<void>();

  sidenavToggle$ = this._sidenavToggle.asObservable();

  constructor() { }


  toggleSidenav() {
    this._sidenavToggle.next();
  }
}
