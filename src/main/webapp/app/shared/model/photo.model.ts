import dayjs from 'dayjs';
import { IPost } from 'app/shared/model/post.model';
import { IProduct } from 'app/shared/model/product.model';

export interface IPhoto {
  id?: number;
  title?: string | null;
  linkToPhotoFile?: string;
  description?: string | null;
  author?: string | null;
  owner?: string | null;
  height?: number | null;
  width?: number | null;
  taken?: string | null;
  uploaded?: string | null;
  post?: IPost | null;
  product?: IProduct | null;
}

export const defaultValue: Readonly<IPhoto> = {};
