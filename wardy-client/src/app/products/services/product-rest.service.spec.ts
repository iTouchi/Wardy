import { TestBed } from '@angular/core/testing';

import { ProductRestService } from './product-rest.service';

describe('ProductService', () => {
  let service: ProductRestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductRestService);
  });

  // it('should be created', () => {
  //   expect(service).toBeTruthy();
  // });
});
