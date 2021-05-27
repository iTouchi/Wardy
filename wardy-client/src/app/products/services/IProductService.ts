import { Product } from "../../models/product";

export interface IProductService {

getAll();
createNewProduct(newProduct: Product);
getOne(id);
update(id, product: Product);
delete(id);

}
