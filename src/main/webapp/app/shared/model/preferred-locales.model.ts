import { ICustomer } from 'app/shared/model/customer.model';

export interface IPreferredLocales {
  id?: number;
  preferredLocales?: string | null;
  customers?: ICustomer[] | null;
}

export const defaultValue: Readonly<IPreferredLocales> = {};
