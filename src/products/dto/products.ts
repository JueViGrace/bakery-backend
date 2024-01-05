import {
  IsArray,
  IsBoolean,
  IsNotEmpty,
  IsNumber,
  IsString,
} from 'class-validator';

export class ProductDto {
  @IsString({ message: 'El nombre debe ser un string' })
  @IsNotEmpty({ message: 'El nombre no puede estar vacio o ser nulo' })
  name: string;
  createdAt: Date;

  @IsBoolean()
  haveStock: boolean;

  @IsNumber()
  stock: number;

  @IsNumber()
  @IsNotEmpty()
  price: number;

  @IsNumber()
  @IsNotEmpty()
  truePrice: number;

  @IsNotEmpty()
  @IsArray()
  categories: any[];

  productImage: Express.Multer.File;

  constructor(
    name: string,
    createdAt: Date,
    stock: number,
    price: number,
    truePrice: number,
    categories: any[],
    productImage: Express.Multer.File,
    haveStock?: boolean,
  ) {
    this.name = name;
    this.createdAt = createdAt;
    this.stock = stock;
    this.price = price;
    this.truePrice = truePrice;
    this.categories = categories;
    this.haveStock = haveStock;
    this.productImage = productImage;
  }
}
