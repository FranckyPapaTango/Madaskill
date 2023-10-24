import post from 'app/entities/post/post.reducer';
import product from 'app/entities/product/product.reducer';
import applicationUser from 'app/entities/application-user/application-user.reducer';
import customer from 'app/entities/customer/customer.reducer';
import invoiceSettings from 'app/entities/invoice-settings/invoice-settings.reducer';
import metadata from 'app/entities/metadata/metadata.reducer';
import preferredLocales from 'app/entities/preferred-locales/preferred-locales.reducer';
import commande from 'app/entities/commande/commande.reducer';
import photo from 'app/entities/photo/photo.reducer';
import invoice from 'app/entities/invoice/invoice.reducer';
import lineItem from 'app/entities/line-item/line-item.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  post,
  product,
  applicationUser,
  customer,
  invoiceSettings,
  metadata,
  preferredLocales,
  commande,
  photo,
  invoice,
  lineItem,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
