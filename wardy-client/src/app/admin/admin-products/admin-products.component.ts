import { Component, OnInit, OnDestroy } from "@angular/core";
import { Subscription } from "rxjs";
import { Product } from "src/app/models/product";
import { DataTableResource } from "angular7-data-table";
import { ProductFirebaseService } from 'src/app/products/services/product-firebase.service';
import { ProductRestService } from 'src/app/products/services/product-rest.service';

@Component({
  selector: "app-admin-products",
  templateUrl: "./admin-products.component.html",
  styleUrls: ["./admin-products.component.css"],
})
export class AdminProductsComponent implements OnInit, OnDestroy {
  products: Product[] = [];
  subscription: Subscription;
  tableResource: DataTableResource<Product>;
  items: Product[] = [];
  itemCount: number;

  constructor(private productService: ProductRestService) {
    this.subscription = this.productService
      .getAll()
      .subscribe((products) => {
        {this.products = products._embedded.productList;}
        this.initializeTable(products._embedded.productList); // Initializing data-table here
      });
  }

  ngOnInit(): void {}

  filter(query: string) {
    let filteredProducts = query
      ? this.products.filter((p) =>
          p.title.toLowerCase().includes(query.toLowerCase())
        )
      : this.products;

    this.initializeTable(filteredProducts);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  reloadItems(params) {
    if (!this.tableResource) return;

    this.tableResource
      .query(params) // Gets all the records for the current page based on the current parameter | offset: 0 means page 1
      .then((items) => (this.items = items));
  }

  private initializeTable(products: Product[]) {
    this.tableResource = new DataTableResource(products);
    this.tableResource
      .query({ offset: 0 }) // Gets all the records for the current page based on the current parameter | offset: 0 means page 1
      .then((items) => (this.items = items));
    this.tableResource
      .count() // returns total records in the table
      .then((count) => (this.itemCount = count));
  }
}
