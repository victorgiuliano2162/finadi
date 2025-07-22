import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovimentacaoDialogComponent } from './movimentacao-dialog.component';

describe('MovimentacaoDialogComponent', () => {
  let component: MovimentacaoDialogComponent;
  let fixture: ComponentFixture<MovimentacaoDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovimentacaoDialogComponent]
    });
    fixture = TestBed.createComponent(MovimentacaoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
