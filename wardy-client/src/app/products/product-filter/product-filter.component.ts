import { Component, Input } from "@angular/core";
import { CategoryFirebaseService } from 'src/app/categories/services/category-firebase.service';
import { CategoryRestService } from 'src/app/categories/services/category-rest.service';
import { Category } from 'src/app/models/category';

@Component({
  selector: "app-product-filter",
  templateUrl: "./product-filter.component.html",
  styleUrls: ["./product-filter.component.css"],
})
export class ProductFilterComponent {
  categories: Category[] = [];
  @Input('category') category;

  constructor(categoryService: CategoryRestService) {
    categoryService.getAll().subscribe((response => {
      this.categories = response._embedded.categoryList;
    }));
  }

}
