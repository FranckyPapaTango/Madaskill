import dayjs from 'dayjs';
import { IPhoto } from 'app/shared/model/photo.model';
import { IApplicationUser } from 'app/shared/model/application-user.model';

export interface IPost {
  id?: number;
  title?: string;
  body?: string | null;
  categorie?: string | null;
  resumee?: string | null;
  auteur?: string | null;
  datePersistence?: string;
  photos?: IPhoto[] | null;
  user?: IApplicationUser | null;
}

export const defaultValue: Readonly<IPost> = {};
