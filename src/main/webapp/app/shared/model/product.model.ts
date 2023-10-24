import { IPhoto } from 'app/shared/model/photo.model';
import { ILineItem } from 'app/shared/model/line-item.model';
import { IApplicationUser } from 'app/shared/model/application-user.model';

export interface IProduct {
  id?: number;
  object?: string | null;
  active?: boolean | null;
  created?: number | null;
  defaultPrice?: number | null;
  description?: string | null;
  livemode?: boolean | null;
  name?: string | null;
  shippable?: boolean | null;
  statementDescriptor?: string | null;
  taxCode?: string | null;
  unitLabel?: string | null;
  updated?: number | null;
  url?: string | null;
  sku?: string | null;
  title?: string;
  linkToGenericPhotoFile?: string | null;
  availableSizes?: string | null;
  currencyFormat?: string | null;
  isFreeShipping?: boolean | null;
  price?: number;
  style?: string | null;
  installments?: number | null;
  photos?: IPhoto[] | null;
  lineItem?: ILineItem | null;
  user?: IApplicationUser | null;
}

export const defaultValue: Readonly<IProduct> = {
  active: false,
  livemode: false,
  shippable: false,
  isFreeShipping: false,
};
