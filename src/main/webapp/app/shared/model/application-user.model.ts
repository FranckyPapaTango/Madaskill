import { IUser } from 'app/shared/model/user.model';
import { IPost } from 'app/shared/model/post.model';
import { IProduct } from 'app/shared/model/product.model';

export interface IApplicationUser {
  id?: number;
  phoneNumber?: string;
  email?: string | null;
  libelleAdresse?: string | null;
  villeTown?: string | null;
  paysCountry?: string;
  internalUser?: IUser | null;
  posts?: IPost[] | null;
  products?: IProduct[] | null;
}

export const defaultValue: Readonly<IApplicationUser> = {};
