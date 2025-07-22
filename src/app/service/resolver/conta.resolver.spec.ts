import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { contaResolver } from './conta.resolver';

describe('contaResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => contaResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
