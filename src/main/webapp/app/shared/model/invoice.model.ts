import { ICommande } from 'app/shared/model/commande.model';
import { ICustomer } from 'app/shared/model/customer.model';

export interface IInvoice {
  id?: string;
  object?: string | null;
  accountCountry?: string | null;
  accountName?: string | null;
  accountTaxIds?: string | null;
  amountDue?: number | null;
  amountPaid?: number | null;
  amountRemaining?: number | null;
  amountShipping?: number | null;
  application?: string | null;
  applicationFeeAmount?: number | null;
  attemptCount?: number | null;
  attempted?: boolean | null;
  autoAdvance?: boolean | null;
  billingReason?: string | null;
  charge?: string | null;
  collectionMethod?: string | null;
  created?: number | null;
  currency?: string | null;
  customFields?: string | null;
  customerStringValue?: string | null;
  customerAddress?: string | null;
  customerEmail?: string | null;
  customerName?: string | null;
  customerPhone?: string | null;
  customerShipping?: string | null;
  customerTaxExempt?: string | null;
  customerTaxIds?: string | null;
  defaultPaymentMethod?: string | null;
  defaultSource?: string | null;
  defaultTaxRates?: string | null;
  description?: string | null;
  discount?: string | null;
  discounts?: string | null;
  dueDate?: number | null;
  effectiveAt?: number | null;
  endingBalance?: number | null;
  footer?: string | null;
  fromInvoice?: string | null;
  hostedInvoiceUrl?: string | null;
  invoicePdf?: string | null;
  lastFinalizationError?: string | null;
  latestRevision?: string | null;
  livemode?: boolean | null;
  metadata?: string | null;
  nextPaymentAttempt?: number | null;
  number?: string | null;
  onBehalfOf?: string | null;
  paid?: boolean | null;
  paidOutOfBand?: boolean | null;
  paymentIntent?: string | null;
  paymentSettings?: string | null;
  periodEnd?: number | null;
  periodStart?: number | null;
  postPaymentCreditNotesAmount?: number | null;
  prePaymentCreditNotesAmount?: number | null;
  quote?: string | null;
  receiptNumber?: string | null;
  rendering?: string | null;
  renderingOptions?: string | null;
  shippingCost?: number | null;
  shippingDetails?: string | null;
  startingBalance?: number | null;
  statementDescriptor?: string | null;
  status?: string | null;
  statusTransitions?: string | null;
  subscription?: string | null;
  subscriptionDetails?: string | null;
  subtotal?: number | null;
  subtotalExcludingTax?: number | null;
  tax?: string | null;
  testClock?: string | null;
  total?: number | null;
  totalDiscountAmounts?: string | null;
  totalExcludingTax?: number | null;
  totalTaxAmounts?: string | null;
  transferData?: string | null;
  webhooksDeliveredAt?: number | null;
  commande?: ICommande | null;
  customer?: ICustomer | null;
}

export const defaultValue: Readonly<IInvoice> = {
  attempted: false,
  autoAdvance: false,
  livemode: false,
  paid: false,
  paidOutOfBand: false,
};
