import { Component, OnInit } from "@angular/core";
import { CategoryFirebaseService } from "src/app/categories/services/category-firebase.service";
import { Router, ActivatedRoute } from "@angular/router";
import { IProductService } from 'src/app/products/services/IProductService';
import { ProductFirebaseService } from 'src/app/products/services/product-firebase.service';
import { ProductRestService } from 'src/app/products/services/product-rest.service';
import { error } from 'protractor';
import { CategoryRestService } from 'src/app/categories/services/category-rest.service';
import { Category } from 'src/app/models/category';

@Component({
  selector: "app-product-form",
  templateUrl: "./product-form.component.html",
  styleUrls: ["./product-form.component.css"],
})
export class ProductFormComponent implements OnInit {
  categories: Category[] = [];
  product = <any>{};
  id;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private categoryService: CategoryRestService,
    private productService: ProductRestService
  ) {
    categoryService.getAll().subscribe((response => {
      this.categories = response._embedded.categoryList;
    }));

    this.id = this.route.snapshot.paramMap.get("id");
    console.log(this.id);
    if (this.id)
      this.productService.getOne(this.id).subscribe((response) => (this.product = response));
  }

  ngOnInit(): void {}

  save(product) {
    if (this.id) this.productService.update(this.id, product).subscribe((response) => {
      console.log(response)
    });
    else this.productService.createNewProduct(product).subscribe((response) => {
      console.log(response);
    }, error => console.log(error));

    this.router.navigate(["/admin/products"]);
  }

  delete() {
    if (!confirm("Are you sure you want to delete this product?")) return;

    this.productService.delete(this.id).subscribe((response) => {
      console.log(response)
    }, error => console.log(error));
    this.router.navigate(["/admin/products"]);
  }
}
