export interface IInvoiceSettings {
  id?: number;
  customFields?: string | null;
  defaultPaymentMethod?: string | null;
  footer?: string | null;
  renderingOptions?: string | null;
}

export const defaultValue: Readonly<IInvoiceSettings> = {};
