import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[onlyNumbers]'
})
export class OnlyNumbersDirective {

  constructor(private el: ElementRef) { }

    @HostListener('input', ['$event']) onInputChange(event: Event) {
    const initialValue = this.el.nativeElement.value;
    // Usa uma expressão regular para remover qualquer coisa que não seja um número
    this.el.nativeElement.value = initialValue.replace(/[^0-9.-]*/g, '');
    if ( initialValue !== this.el.nativeElement.value) {
      event.stopPropagation();
    }
  }

}
