import { async, ComponentFixture, TestBed } from "@angular/core/testing";

import { ProductsComponent } from "./products.component";
import { ActivatedRoute, Routes } from "@angular/router";
import { RouterTestingModule } from "@angular/router/testing";
import { Component, InjectionToken } from "@angular/core";
import { ProductRestService } from "./services/product-rest.service";
import { HttpClient, HttpHandler } from "@angular/common/http";
import { BrowserModule } from "@angular/platform-browser";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { AngularFireModule } from '@angular/fire';
import { environment } from 'src/environments/environment';
import { AngularFirestoreModule } from '@angular/fire/firestore/public_api';
import { AngularFireStorageModule } from '@angular/fire/storage/public_api';
import { AngularFireAuthModule } from '@angular/fire/auth/public_api';
import { AngularFireDatabaseModule } from '@angular/fire/database/database.module';

@Component({
  selector: "as-test-cmp",
  template: '<div class="title">Hello test</div>',
})
class TestRouterComponent {}

let config: Routes = [
  {
    path: "",
    component: TestRouterComponent,
  },
];

describe("ProductsComponent", () => {
  let component: ProductsComponent;
  let fixture: ComponentFixture<ProductsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [ProductRestService, HttpClient, HttpHandler],
      imports: [
        RouterTestingModule,
        BrowserModule,
        HttpClientTestingModule,
        AngularFireModule.initializeApp(environment.firebase),
  ],
      declarations: [ProductsComponent],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
