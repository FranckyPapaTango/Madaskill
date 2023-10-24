import dayjs from 'dayjs';
import { IInvoiceSettings } from 'app/shared/model/invoice-settings.model';
import { IMetadata } from 'app/shared/model/metadata.model';
import { IInvoice } from 'app/shared/model/invoice.model';
import { IPreferredLocales } from 'app/shared/model/preferred-locales.model';

export interface ICustomer {
  id?: string;
  object?: string | null;
  address?: string | null;
  balance?: number | null;
  created?: number | null;
  currency?: string | null;
  defaultSource?: string | null;
  delinquent?: boolean | null;
  description?: string | null;
  discount?: number | null;
  email?: string | null;
  invoicePrefix?: string | null;
  livemode?: boolean | null;
  name?: string | null;
  nextInvoiceSequence?: number | null;
  phone?: string | null;
  shipping?: string | null;
  taxExempt?: string | null;
  testClock?: string | null;
  invoiceSettings?: IInvoiceSettings | null;
  metadata?: IMetadata | null;
  invoices?: IInvoice[] | null;
  preferredLocales?: IPreferredLocales[] | null;
}

export const defaultValue: Readonly<ICustomer> = {
  delinquent: false,
  livemode: false,
};
