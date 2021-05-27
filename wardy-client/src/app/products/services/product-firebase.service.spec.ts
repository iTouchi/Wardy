import { TestBed } from '@angular/core/testing';

import { ProductFirebaseService as ProductFirebaseService } from './product-firebase.service';

describe('ProductService', () => {
  let service: ProductFirebaseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductFirebaseService);
  });

  // it('should be created', () => {
  //   expect(service).toBeTruthy();
  // });
});
