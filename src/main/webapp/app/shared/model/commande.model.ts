import dayjs from 'dayjs';
import { ILineItem } from 'app/shared/model/line-item.model';
import { IInvoice } from 'app/shared/model/invoice.model';

export interface ICommande {
  id?: number;
  dateCommande?: string;
  tva?: number;
  taxesTotales?: number;
  montantAmountTtc?: number;
  isPayedIsFacture?: boolean;
  lineItems?: ILineItem[] | null;
  customer?: IInvoice | null;
}

export const defaultValue: Readonly<ICommande> = {
  isPayedIsFacture: false,
};
