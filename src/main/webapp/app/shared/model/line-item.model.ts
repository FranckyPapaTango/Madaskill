import { IProduct } from 'app/shared/model/product.model';
import { ICommande } from 'app/shared/model/commande.model';

export interface ILineItem {
  id?: string;
  object?: string | null;
  amount?: number | null;
  amountExcludingTax?: number | null;
  currency?: string | null;
  description?: string | null;
  discountAmounts?: string | null;
  discountable?: boolean | null;
  discounts?: string | null;
  invoiceItem?: string | null;
  livemode?: boolean | null;
  metadata?: string | null;
  periodEnd?: number | null;
  periodStart?: number | null;
  price?: string | null;
  proration?: boolean | null;
  prorationDetails?: string | null;
  quantity?: number | null;
  subscription?: string | null;
  taxAmounts?: string | null;
  taxRates?: string | null;
  type?: string | null;
  unitAmountExcludingTax?: string | null;
  product?: IProduct | null;
  commande?: ICommande | null;
}

export const defaultValue: Readonly<ILineItem> = {
  discountable: false,
  livemode: false,
  proration: false,
};
