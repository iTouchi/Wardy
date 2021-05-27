import { TestBed } from '@angular/core/testing';
import { CategoryFirebaseService } from './category-firebase.service';


describe('CategoryService', () => {
  let service: CategoryFirebaseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CategoryFirebaseService);
  });

  // it('should be created', () => {
  //   expect(service).toBeTruthy();
  // });
});
