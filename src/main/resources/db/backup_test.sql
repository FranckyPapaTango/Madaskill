PGDMP         
                |        	   Madaskill    11.16    11.16 N    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �           1262    639887 	   Madaskill    DATABASE     �   CREATE DATABASE "Madaskill" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_France.1252' LC_CTYPE = 'French_France.1252';
    DROP DATABASE "Madaskill";
             root    false            �            1259    672844    application_user    TABLE     7  CREATE TABLE public.application_user (
    id bigint NOT NULL,
    phone_number character varying(255) NOT NULL,
    email character varying(255),
    libelle_adresse character varying(255),
    ville_town character varying(255),
    pays_country character varying(255) NOT NULL,
    internal_user_id bigint
);
 $   DROP TABLE public.application_user;
       public         root    false            �            1259    672889    commande    TABLE       CREATE TABLE public.commande (
    id bigint NOT NULL,
    date_commande timestamp without time zone NOT NULL,
    tva double precision NOT NULL,
    taxes_totales double precision NOT NULL,
    montant_amount_ttc double precision NOT NULL,
    is_payed_is_facture boolean NOT NULL
);
    DROP TABLE public.commande;
       public         root    false            �            1259    672854    customer    TABLE     �  CREATE TABLE public.customer (
    id character varying(255) NOT NULL,
    object character varying(255),
    address character varying(255),
    balance integer,
    created bigint,
    currency character varying(255),
    default_source character varying(255),
    delinquent boolean,
    description character varying(255),
    discount integer,
    email character varying(255),
    invoice_prefix character varying(255),
    livemode boolean,
    name character varying(255),
    next_invoice_sequence integer,
    phone character varying(255),
    shipping character varying(255),
    tax_exempt character varying(255),
    test_clock timestamp without time zone,
    invoice_settings_id bigint,
    metadata_id bigint
);
    DROP TABLE public.customer;
       public         root    false            �            1259    672788    databasechangelog    TABLE     Y  CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);
 %   DROP TABLE public.databasechangelog;
       public         root    false            �            1259    672783    databasechangeloglock    TABLE     �   CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);
 )   DROP TABLE public.databasechangeloglock;
       public         root    false            �            1259    672902    invoice    TABLE     �  CREATE TABLE public.invoice (
    id character varying(255) NOT NULL,
    object character varying(255),
    account_country character varying(255),
    account_name character varying(255),
    account_tax_ids character varying(255),
    amount_due bigint,
    amount_paid bigint,
    amount_remaining bigint,
    amount_shipping bigint,
    application character varying(255),
    application_fee_amount bigint,
    attempt_count integer,
    attempted boolean,
    auto_advance boolean,
    billing_reason character varying(255),
    charge character varying(255),
    collection_method character varying(255),
    created bigint,
    currency character varying(255),
    custom_fields character varying(255),
    customer_string_value character varying(255),
    customer_address character varying(255),
    customer_email character varying(255),
    customer_name character varying(255),
    customer_phone character varying(255),
    customer_shipping character varying(255),
    customer_tax_exempt character varying(255),
    customer_tax_ids character varying(255),
    default_payment_method character varying(255),
    default_source character varying(255),
    default_tax_rates character varying(255),
    description character varying(255),
    discount character varying(255),
    discounts character varying(255),
    due_date bigint,
    effective_at bigint,
    ending_balance bigint,
    footer character varying(255),
    from_invoice character varying(255),
    hosted_invoice_url character varying(255),
    invoice_pdf character varying(255),
    last_finalization_error character varying(255),
    latest_revision character varying(255),
    livemode boolean,
    metadata character varying(255),
    next_payment_attempt bigint,
    number character varying(255),
    on_behalf_of character varying(255),
    paid boolean,
    paid_out_of_band boolean,
    payment_intent character varying(255),
    payment_settings character varying(255),
    period_end bigint,
    period_start bigint,
    post_payment_credit_notes_amount bigint,
    pre_payment_credit_notes_amount bigint,
    quote character varying(255),
    receipt_number character varying(255),
    rendering character varying(255),
    rendering_options character varying(255),
    shipping_cost bigint,
    shipping_details character varying(255),
    starting_balance bigint,
    statement_descriptor character varying(255),
    status character varying(255),
    status_transitions character varying(255),
    subscription character varying(255),
    subscription_details character varying(255),
    subtotal bigint,
    subtotal_excluding_tax bigint,
    tax character varying(255),
    test_clock character varying(255),
    total bigint,
    total_discount_amounts character varying(255),
    total_excluding_tax bigint,
    total_tax_amounts character varying(255),
    transfer_data character varying(255),
    webhooks_delivered_at bigint,
    commande_id bigint,
    customer_id character varying(255)
);
    DROP TABLE public.invoice;
       public         root    false            �            1259    672871    invoice_settings    TABLE     �   CREATE TABLE public.invoice_settings (
    id bigint NOT NULL,
    custom_fields character varying(255),
    default_payment_method character varying(255),
    footer character varying(255),
    rendering_options character varying(255)
);
 $   DROP TABLE public.invoice_settings;
       public         root    false            �            1259    672808    jhi_authority    TABLE     O   CREATE TABLE public.jhi_authority (
    name character varying(50) NOT NULL
);
 !   DROP TABLE public.jhi_authority;
       public         root    false            �            1259    672796    jhi_user    TABLE     �  CREATE TABLE public.jhi_user (
    id bigint NOT NULL,
    login character varying(50) NOT NULL,
    password_hash character varying(60) NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    email character varying(191),
    image_url character varying(256),
    activated boolean NOT NULL,
    lang_key character varying(10),
    activation_key character varying(20),
    reset_key character varying(20),
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    reset_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
    DROP TABLE public.jhi_user;
       public         root    false            �            1259    672813    jhi_user_authority    TABLE     {   CREATE TABLE public.jhi_user_authority (
    user_id bigint NOT NULL,
    authority_name character varying(50) NOT NULL
);
 &   DROP TABLE public.jhi_user_authority;
       public         root    false            �            1259    672912 	   line_item    TABLE     i  CREATE TABLE public.line_item (
    id character varying(255) NOT NULL,
    object character varying(255),
    amount bigint,
    amount_excluding_tax bigint,
    currency character varying(255),
    description character varying(255),
    discount_amounts character varying(255),
    discountable boolean,
    discounts character varying(255),
    invoice_item character varying(255),
    livemode boolean,
    metadata character varying(255),
    period_end bigint,
    period_start bigint,
    price character varying(255),
    proration boolean,
    proration_details character varying(255),
    quantity integer,
    subscription character varying(255),
    tax_amounts character varying(255),
    tax_rates character varying(255),
    type character varying(255),
    unit_amount_excluding_tax character varying(255),
    product_id bigint,
    commande_id bigint
);
    DROP TABLE public.line_item;
       public         root    false            �            1259    672879    metadata    TABLE     ^   CREATE TABLE public.metadata (
    id bigint NOT NULL,
    order_id character varying(255)
);
    DROP TABLE public.metadata;
       public         root    false            �            1259    672894    photo    TABLE     �  CREATE TABLE public.photo (
    id bigint NOT NULL,
    title character varying(255),
    link_to_photo_file character varying(255) NOT NULL,
    description character varying(255),
    author character varying(255),
    owner character varying(255),
    height integer,
    width integer,
    taken timestamp without time zone,
    uploaded timestamp without time zone,
    post_id bigint,
    product_id bigint
);
    DROP TABLE public.photo;
       public         root    false            �            1259    672828    post    TABLE     =  CREATE TABLE public.post (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    body character varying(255),
    categorie character varying(255),
    resumee character varying(255),
    auteur character varying(255),
    date_persistence timestamp without time zone NOT NULL,
    user_id bigint
);
    DROP TABLE public.post;
       public         root    false            �            1259    672884    preferred_locales    TABLE     p   CREATE TABLE public.preferred_locales (
    id bigint NOT NULL,
    preferred_locales character varying(255)
);
 %   DROP TABLE public.preferred_locales;
       public         root    false            �            1259    672836    product    TABLE     ;  CREATE TABLE public.product (
    id bigint NOT NULL,
    object character varying(255),
    active boolean,
    created bigint,
    default_price double precision,
    description character varying(255),
    livemode boolean,
    name character varying(255),
    shippable boolean,
    statement_descriptor character varying(255),
    tax_code character varying(255),
    unit_label character varying(255),
    updated bigint,
    url character varying(255),
    sku character varying(255),
    title character varying(255) NOT NULL,
    link_to_generic_photo_file character varying(255),
    available_sizes character varying(255),
    currency_format character varying(255),
    is_free_shipping boolean,
    price double precision NOT NULL,
    style character varying(255),
    installments integer,
    user_id bigint
);
    DROP TABLE public.product;
       public         root    false            �            1259    672866    rel_customer__preferred_locales    TABLE     �   CREATE TABLE public.rel_customer__preferred_locales (
    preferred_locales_id bigint NOT NULL,
    customer_id character varying(255) NOT NULL
);
 3   DROP TABLE public.rel_customer__preferred_locales;
       public         root    false            �            1259    672794    sequence_generator    SEQUENCE        CREATE SEQUENCE public.sequence_generator
    START WITH 1050
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.sequence_generator;
       public       root    false            �          0    672844    application_user 
   TABLE DATA               �   COPY public.application_user (id, phone_number, email, libelle_adresse, ville_town, pays_country, internal_user_id) FROM stdin;
    public       root    false    204   �       �          0    672889    commande 
   TABLE DATA               r   COPY public.commande (id, date_commande, tva, taxes_totales, montant_amount_ttc, is_payed_is_facture) FROM stdin;
    public       root    false    210   O�       �          0    672854    customer 
   TABLE DATA                 COPY public.customer (id, object, address, balance, created, currency, default_source, delinquent, description, discount, email, invoice_prefix, livemode, name, next_invoice_sequence, phone, shipping, tax_exempt, test_clock, invoice_settings_id, metadata_id) FROM stdin;
    public       root    false    205   0�       ~          0    672788    databasechangelog 
   TABLE DATA               �   COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
    public       root    false    197   ŉ       }          0    672783    databasechangeloglock 
   TABLE DATA               R   COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
    public       root    false    196   ^�       �          0    672902    invoice 
   TABLE DATA               �  COPY public.invoice (id, object, account_country, account_name, account_tax_ids, amount_due, amount_paid, amount_remaining, amount_shipping, application, application_fee_amount, attempt_count, attempted, auto_advance, billing_reason, charge, collection_method, created, currency, custom_fields, customer_string_value, customer_address, customer_email, customer_name, customer_phone, customer_shipping, customer_tax_exempt, customer_tax_ids, default_payment_method, default_source, default_tax_rates, description, discount, discounts, due_date, effective_at, ending_balance, footer, from_invoice, hosted_invoice_url, invoice_pdf, last_finalization_error, latest_revision, livemode, metadata, next_payment_attempt, number, on_behalf_of, paid, paid_out_of_band, payment_intent, payment_settings, period_end, period_start, post_payment_credit_notes_amount, pre_payment_credit_notes_amount, quote, receipt_number, rendering, rendering_options, shipping_cost, shipping_details, starting_balance, statement_descriptor, status, status_transitions, subscription, subscription_details, subtotal, subtotal_excluding_tax, tax, test_clock, total, total_discount_amounts, total_excluding_tax, total_tax_amounts, transfer_data, webhooks_delivered_at, commande_id, customer_id) FROM stdin;
    public       root    false    212   ��       �          0    672871    invoice_settings 
   TABLE DATA               p   COPY public.invoice_settings (id, custom_fields, default_payment_method, footer, rendering_options) FROM stdin;
    public       root    false    207   ��       �          0    672808    jhi_authority 
   TABLE DATA               -   COPY public.jhi_authority (name) FROM stdin;
    public       root    false    200   \�       �          0    672796    jhi_user 
   TABLE DATA               �   COPY public.jhi_user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date) FROM stdin;
    public       root    false    199   ��       �          0    672813    jhi_user_authority 
   TABLE DATA               E   COPY public.jhi_user_authority (user_id, authority_name) FROM stdin;
    public       root    false    201   Z�       �          0    672912 	   line_item 
   TABLE DATA               Z  COPY public.line_item (id, object, amount, amount_excluding_tax, currency, description, discount_amounts, discountable, discounts, invoice_item, livemode, metadata, period_end, period_start, price, proration, proration_details, quantity, subscription, tax_amounts, tax_rates, type, unit_amount_excluding_tax, product_id, commande_id) FROM stdin;
    public       root    false    213   ��       �          0    672879    metadata 
   TABLE DATA               0   COPY public.metadata (id, order_id) FROM stdin;
    public       root    false    208   į       �          0    672894    photo 
   TABLE DATA               �   COPY public.photo (id, title, link_to_photo_file, description, author, owner, height, width, taken, uploaded, post_id, product_id) FROM stdin;
    public       root    false    211   y�       �          0    672828    post 
   TABLE DATA               f   COPY public.post (id, title, body, categorie, resumee, auteur, date_persistence, user_id) FROM stdin;
    public       root    false    202   ��       �          0    672884    preferred_locales 
   TABLE DATA               B   COPY public.preferred_locales (id, preferred_locales) FROM stdin;
    public       root    false    209   �       �          0    672836    product 
   TABLE DATA               0  COPY public.product (id, object, active, created, default_price, description, livemode, name, shippable, statement_descriptor, tax_code, unit_label, updated, url, sku, title, link_to_generic_photo_file, available_sizes, currency_format, is_free_shipping, price, style, installments, user_id) FROM stdin;
    public       root    false    203    �       �          0    672866    rel_customer__preferred_locales 
   TABLE DATA               \   COPY public.rel_customer__preferred_locales (preferred_locales_id, customer_id) FROM stdin;
    public       root    false    206   ��       �           0    0    sequence_generator    SEQUENCE SET     C   SELECT pg_catalog.setval('public.sequence_generator', 1050, true);
            public       root    false    198            �
           2606    672851 &   application_user application_user_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.application_user
    ADD CONSTRAINT application_user_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.application_user DROP CONSTRAINT application_user_pkey;
       public         root    false    204            �
           2606    672893    commande commande_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.commande
    ADD CONSTRAINT commande_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.commande DROP CONSTRAINT commande_pkey;
       public         root    false    210            �
           2606    672861    customer customer_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public         root    false    205            �
           2606    672787 0   databasechangeloglock databasechangeloglock_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public.databasechangeloglock DROP CONSTRAINT databasechangeloglock_pkey;
       public         root    false    196            �
           2606    672909    invoice invoice_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT invoice_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.invoice DROP CONSTRAINT invoice_pkey;
       public         root    false    212            �
           2606    672878 &   invoice_settings invoice_settings_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.invoice_settings
    ADD CONSTRAINT invoice_settings_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.invoice_settings DROP CONSTRAINT invoice_settings_pkey;
       public         root    false    207            �
           2606    672812     jhi_authority jhi_authority_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.jhi_authority
    ADD CONSTRAINT jhi_authority_pkey PRIMARY KEY (name);
 J   ALTER TABLE ONLY public.jhi_authority DROP CONSTRAINT jhi_authority_pkey;
       public         root    false    200            �
           2606    672817 *   jhi_user_authority jhi_user_authority_pkey 
   CONSTRAINT     }   ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT jhi_user_authority_pkey PRIMARY KEY (user_id, authority_name);
 T   ALTER TABLE ONLY public.jhi_user_authority DROP CONSTRAINT jhi_user_authority_pkey;
       public         root    false    201    201            �
           2606    672803    jhi_user jhi_user_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT jhi_user_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.jhi_user DROP CONSTRAINT jhi_user_pkey;
       public         root    false    199            �
           2606    672919    line_item line_item_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT line_item_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.line_item DROP CONSTRAINT line_item_pkey;
       public         root    false    213            �
           2606    672883    metadata metadata_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.metadata
    ADD CONSTRAINT metadata_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.metadata DROP CONSTRAINT metadata_pkey;
       public         root    false    208            �
           2606    672901    photo photo_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.photo
    ADD CONSTRAINT photo_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.photo DROP CONSTRAINT photo_pkey;
       public         root    false    211            �
           2606    672835    post post_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.post DROP CONSTRAINT post_pkey;
       public         root    false    202            �
           2606    672888 (   preferred_locales preferred_locales_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.preferred_locales
    ADD CONSTRAINT preferred_locales_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.preferred_locales DROP CONSTRAINT preferred_locales_pkey;
       public         root    false    209            �
           2606    672843    product product_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
       public         root    false    203            �
           2606    672870 D   rel_customer__preferred_locales rel_customer__preferred_locales_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.rel_customer__preferred_locales
    ADD CONSTRAINT rel_customer__preferred_locales_pkey PRIMARY KEY (customer_id, preferred_locales_id);
 n   ALTER TABLE ONLY public.rel_customer__preferred_locales DROP CONSTRAINT rel_customer__preferred_locales_pkey;
       public         root    false    206    206            �
           2606    672853 6   application_user ux_application_user__internal_user_id 
   CONSTRAINT     }   ALTER TABLE ONLY public.application_user
    ADD CONSTRAINT ux_application_user__internal_user_id UNIQUE (internal_user_id);
 `   ALTER TABLE ONLY public.application_user DROP CONSTRAINT ux_application_user__internal_user_id;
       public         root    false    204            �
           2606    672863 )   customer ux_customer__invoice_settings_id 
   CONSTRAINT     s   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT ux_customer__invoice_settings_id UNIQUE (invoice_settings_id);
 S   ALTER TABLE ONLY public.customer DROP CONSTRAINT ux_customer__invoice_settings_id;
       public         root    false    205            �
           2606    672865 !   customer ux_customer__metadata_id 
   CONSTRAINT     c   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT ux_customer__metadata_id UNIQUE (metadata_id);
 K   ALTER TABLE ONLY public.customer DROP CONSTRAINT ux_customer__metadata_id;
       public         root    false    205            �
           2606    672911    invoice ux_invoice__commande_id 
   CONSTRAINT     a   ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT ux_invoice__commande_id UNIQUE (commande_id);
 I   ALTER TABLE ONLY public.invoice DROP CONSTRAINT ux_invoice__commande_id;
       public         root    false    212            �
           2606    672921 "   line_item ux_line_item__product_id 
   CONSTRAINT     c   ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT ux_line_item__product_id UNIQUE (product_id);
 L   ALTER TABLE ONLY public.line_item DROP CONSTRAINT ux_line_item__product_id;
       public         root    false    213            �
           2606    672807    jhi_user ux_user_email 
   CONSTRAINT     R   ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_email UNIQUE (email);
 @   ALTER TABLE ONLY public.jhi_user DROP CONSTRAINT ux_user_email;
       public         root    false    199            �
           2606    672805    jhi_user ux_user_login 
   CONSTRAINT     R   ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_login UNIQUE (login);
 @   ALTER TABLE ONLY public.jhi_user DROP CONSTRAINT ux_user_login;
       public         root    false    199            �
           2606    672932 6   application_user fk_application_user__internal_user_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.application_user
    ADD CONSTRAINT fk_application_user__internal_user_id FOREIGN KEY (internal_user_id) REFERENCES public.jhi_user(id);
 `   ALTER TABLE ONLY public.application_user DROP CONSTRAINT fk_application_user__internal_user_id;
       public       root    false    204    199    2762            �
           2606    672818 $   jhi_user_authority fk_authority_name    FK CONSTRAINT     �   ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES public.jhi_authority(name);
 N   ALTER TABLE ONLY public.jhi_user_authority DROP CONSTRAINT fk_authority_name;
       public       root    false    2768    200    201            �
           2606    672937 )   customer fk_customer__invoice_settings_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT fk_customer__invoice_settings_id FOREIGN KEY (invoice_settings_id) REFERENCES public.invoice_settings(id);
 S   ALTER TABLE ONLY public.customer DROP CONSTRAINT fk_customer__invoice_settings_id;
       public       root    false    205    207    2788            �
           2606    672942 !   customer fk_customer__metadata_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT fk_customer__metadata_id FOREIGN KEY (metadata_id) REFERENCES public.metadata(id);
 K   ALTER TABLE ONLY public.customer DROP CONSTRAINT fk_customer__metadata_id;
       public       root    false    205    208    2790                        2606    672967    invoice fk_invoice__commande_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT fk_invoice__commande_id FOREIGN KEY (commande_id) REFERENCES public.commande(id);
 I   ALTER TABLE ONLY public.invoice DROP CONSTRAINT fk_invoice__commande_id;
       public       root    false    212    2794    210                       2606    672972    invoice fk_invoice__customer_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT fk_invoice__customer_id FOREIGN KEY (customer_id) REFERENCES public.customer(id);
 I   ALTER TABLE ONLY public.invoice DROP CONSTRAINT fk_invoice__customer_id;
       public       root    false    2780    212    205                       2606    672982 #   line_item fk_line_item__commande_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT fk_line_item__commande_id FOREIGN KEY (commande_id) REFERENCES public.commande(id);
 M   ALTER TABLE ONLY public.line_item DROP CONSTRAINT fk_line_item__commande_id;
       public       root    false    2794    210    213                       2606    672977 "   line_item fk_line_item__product_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT fk_line_item__product_id FOREIGN KEY (product_id) REFERENCES public.product(id);
 L   ALTER TABLE ONLY public.line_item DROP CONSTRAINT fk_line_item__product_id;
       public       root    false    2774    203    213            �
           2606    672957    photo fk_photo__post_id    FK CONSTRAINT     u   ALTER TABLE ONLY public.photo
    ADD CONSTRAINT fk_photo__post_id FOREIGN KEY (post_id) REFERENCES public.post(id);
 A   ALTER TABLE ONLY public.photo DROP CONSTRAINT fk_photo__post_id;
       public       root    false    211    202    2772            �
           2606    672962    photo fk_photo__product_id    FK CONSTRAINT     ~   ALTER TABLE ONLY public.photo
    ADD CONSTRAINT fk_photo__product_id FOREIGN KEY (product_id) REFERENCES public.product(id);
 D   ALTER TABLE ONLY public.photo DROP CONSTRAINT fk_photo__product_id;
       public       root    false    203    211    2774            �
           2606    672922    post fk_post__user_id    FK CONSTRAINT        ALTER TABLE ONLY public.post
    ADD CONSTRAINT fk_post__user_id FOREIGN KEY (user_id) REFERENCES public.application_user(id);
 ?   ALTER TABLE ONLY public.post DROP CONSTRAINT fk_post__user_id;
       public       root    false    204    2776    202            �
           2606    672927    product fk_product__user_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_product__user_id FOREIGN KEY (user_id) REFERENCES public.application_user(id);
 E   ALTER TABLE ONLY public.product DROP CONSTRAINT fk_product__user_id;
       public       root    false    203    204    2776            �
           2606    672947 O   rel_customer__preferred_locales fk_rel_customer__preferred_locales__customer_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.rel_customer__preferred_locales
    ADD CONSTRAINT fk_rel_customer__preferred_locales__customer_id FOREIGN KEY (customer_id) REFERENCES public.customer(id);
 y   ALTER TABLE ONLY public.rel_customer__preferred_locales DROP CONSTRAINT fk_rel_customer__preferred_locales__customer_id;
       public       root    false    206    205    2780            �
           2606    672952 X   rel_customer__preferred_locales fk_rel_customer__preferred_locales__preferred_locales_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.rel_customer__preferred_locales
    ADD CONSTRAINT fk_rel_customer__preferred_locales__preferred_locales_id FOREIGN KEY (preferred_locales_id) REFERENCES public.preferred_locales(id);
 �   ALTER TABLE ONLY public.rel_customer__preferred_locales DROP CONSTRAINT fk_rel_customer__preferred_locales__preferred_locales_id;
       public       root    false    206    209    2792            �
           2606    672823    jhi_user_authority fk_user_id    FK CONSTRAINT        ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.jhi_user(id);
 G   ALTER TABLE ONLY public.jhi_user_authority DROP CONSTRAINT fk_user_id;
       public       root    false    199    201    2762            �   _  x�US�n�0=�����ddu�[��5�l�
��Qc�5�Q�$Q�(�^�����4�"ŷ��>�z��h�j>�)G��&���9Z�SA>�W;�� z��BW�e���(ۖ<$tѬ;5�`*G�/�_ɫi���������5;��]g䰰�U0n8��U���[^3�7h�
lS4�pK.rA�����5�6U���Z+��'u-ؤ��ш@a>�i
��ٚu
T�g��5�Wҵ���AZM�\�g��'�G�ޔ"W��C/[P� �s�%'�V����h�c�"�c�����p�+v������c�t�=�S:����ʊ��h�4���G<�ە�z�<6M]t-� ��M�t�
��u�����F0֚���J�d��߾��$�Z�lۚ����ގ����`IP�;v01RO�����'�P�?7� �]ih�Og��k�^���<����5YCII�Ty9(a�֢�QO�%�Z�LVҶ�*Z-�ۍ��k�	�{(PY.��E��Ü�sIN��6"|t�'b�H+�Qpk1�@��-�/m��>$z3��'.Kz��|���t�����d�bݩim� ѣ���W+���I:�^RټMP�y?�L�      �   �   x�]��mA��(��^�+�����|j��RV{
?�jc>(*vv�P��K�H�v�R�E�QFe%��N�%S��	�F'}_�AF����B լ\'����=���S]�_��u�Aָ��"ݙ��3��l�f=�ڠv�[�7X�-���=�C˩D�wy�k}W�!k#KW�%	hl�-�`&�-��u~�����`L�      �   �  x�]�ݎ�8���������?���3�df�i���`@Q%��$z(���~�`^l�����7�e�u�s(���-��RLoY���&�#��zWr���B�N�k�B��!tDj�,�VKI�Ӵ`N߀��!M@z���4�y)���/0��(|ŧ,�xi8ٖ��0��.�yzy���p��iZ؛<����T�|\�X�c�S���JQŭ�ZrK~K�L�`�Dr���L**䆋�����y[�7�q��h��� փwъ��~��7y� ��晾	���y>�q�0ZX�X.<پ�#1OO�&��-OK�&@Ҵ��!��7��y(����\��]��+,S�>�L�h� \J��^�|���.�k.]��KbK�?�C��1��r)�i7�ImU��m��J��R50�;���v���#Ll�k�@���u�;ld���$����8!'�5�`�/a��;�~��x"���͡t��sC�&��.�s��L�
v�O^�y�*��`��S8�\��p����pDEz���8!=~uN�ǅP�6�MX`�sQ�T8�0-�_�aN�➡�7�o�{��[�[�Z��LKX�eR�@}�1�(|��Ә�c�����u�J-�q�`7y\�PJ8��5�/�6x�%�8@�JՎ,���z,����iI��`iF��!���iv����nO�zt�o��)"�.�=��b
N&�F5�!87�[�ʑk@�Nt��g��/���b����Ui♰�i��F��~"�{�FoÙ�Q1��UX0�v޵>�_�wf��}^��ؖ��h$�Ťw��g!׈E{HK>���:S�O��ho���vХ����J��܅6w7oQ���������On���%=_	2B�ӐW+�g{��%�M6�z�ٵx#:��)^1gC��h��-N�[�>��m����بQVxK�Ҫ61ti?�4�e}t��'}^�Z�Q�V�K٣M�)�Shg�~a8���Z�S�3[=d���	�8O,����Q�BߣW����7���ύ%�İ�f�='*�?C��H�Q�Q�(';�0�:�4X�Dǔ���]�l ��7lK�k�h=���X�+��c�È~���]O�1sZz�
*6�݈� (��.Xf('$
a'��p��b/Ш	Go
tiAa~0a����U/n��n��zF��.�p�7�y"C�s�g�
��Nղ�q��N[��c���P_D�*�H�F"�ZXD��O,��C��]��u�\���<�H�Ղ��6̨c@e�F�Ť�Ǳ�����K��Q����e�_l��&|ZsQ>��tt�kr-�ɯ�('�z��ԱB�/cJn�A���0p�I-Yp\3��NFm4	�#b�}��B�����g�Ҫ��01y���:�_��a=_Ev�GG��ݖxHK�I�)��p2γr�1A���7Ð�jۅ�G<ݖ��_١�uĿUcG�P^�ǨrH�9R$�)S=eGN*�i��֯iH/����֟p����'�M�,�q�q�,o�kD�LGԢ4=��ۑ�Ao� �s���d�4=��b>���4�m�>'th�p����g5����І)��`�s�Pa��<6�nx�S��,7^(o�3@�am*��%����ŋ��T�      ~   �  x��Z�n#7}��
��a�ZL����,l6���>�d�֎.��Z����ly��tK��L|��&U}�!������G����ö�M�׫:�{�����'���|O�;^���vf��|5o紘m�=/����E���7
n�_���|����4���ݯ?�o�Y�Kƒ �+�!y� Y��������x��j{��gZ����x�jכ�����ނ�U���˙�	����M�7j�� �>���T$�!���d���Pe�| ��҂����G�r��ݖ7�__�]{����ϧ���x$���f�������c�����n%c߭W�vC�U{�'�㉙�W����!�����J^�����=)��k@8�op�f�l�I3�.3^��������rCc��+�s�9*k�#�N!%*�!�@�=H���f��+��?i�cad�[�G�5{��v.xd_��}/7�n�m�q**o{L;�fg�PuɅ��e}M�Y����[;���'���`���
�f]v�8�L���XUv3�J
%yqP���~H��P����]
��M�A*�ާ*����8���!D�u��_#<+��"�������b����W�J����	�8� "��TS��L�J�'��p@Lz	���ƫ�'�:5����cԚFt�^O�X�J�_Y(��{
�Y���M�ݶ]/O ��Gm��q����mR>)�1��"9d9��f͇X�Su��v����g��5!��lx����O�	l0�uLT��XKBK�E96U��蒓 bP��۞=��6\y��{Rh��aGqf�e�M�P�f (�@Ǫr��_q����j���٦�0��J}+���S���B�q���<�on���n;��t�j�Y���r���l��d!��s�z`�̟Bζ��ㅵ�����Y��:>�P�󠲍^�/p�L���+ƧG�S�����캈��-���*G��:��fȬ�.�C��T���!ɝ���C��ʺ��^�3h�g��I����T0	`D&�T"W����<+��"�m����8!�����s��s�{�nUN�e`L����ӱ���d�R�iٶ_�EV���59+1��]��*{#f�߿�@}V�0E���Z/��*<�5(�^�vܜa��E|@Q �NN2��Z�h5�q�F���C�{��_a�Ҹ�& ������ڐR4>c%	���-�\ν'uȳ+��|�����z��N6��88�R�@CH5g�T戥 ����^�G:�s����9��=�\��6~����r�&ԍ��:]&�V1�1ym9x�	Q��7��wv��)+%���ax���Q�ޡ뫱Z-�ߥ������Wc3^�8Y�KQʇ���x7*Rۦ謵^K�G���OC�כ�@<#�V�2����ly9�0B�m��]��V�ɺ�sv�Ce�.�0��B���m���')y9PT���VI
��rD��$oB�:&��׃�B<�!Ս����/�����)���Ǧ�_�7j�čmB�)��	͸������h߿>{j��%7?:�}���L��ۂ)��	��4.�Elt��'�Z!/́���u��$�>���X�b�r�t�Ѩ�Ш�i�)�B\x�;��G�UJ���Jq-�:�z.ɡ��|�F�C�Wcf3�ț-N3���钞���0�g[G�18Ik�[����䴤��$���Y�	���*������G�K��uOb����>�w�u�`xp��?�"%��9'�"��f������޳# v�*b�)0�@rd�،��ܛo�fO�{����P�C~�u铬8E�8��93��8���8��H��bcMU�}$cP<�|z@{.�G}�;�K�<��K��}�c�A/|.��|����;������śN�BX��%����sD���)rVvZ��^_a�_.�^�c�v��9)�o�o޼����      }   E   x�3�,�4202�50�50U04�2��26ӳ00240�,JLK,�/��,Q�047�3��36�3������ �:      �      x�mZ�v�F�]?E�j�n 0/9Ȓ�D�Ŕ��uj "3�B"�H���6��Ӌ�������HZ�>Gd��/��JFQ{2M}/���+ª��H��_����h��O�ck��F\��Kw4��.͹����=i�ɪF\�����m[��/�,u�Q��2&��RR�yLi�������4�B�1�B{�W��T4/׷��N��Ms�~}'n,n`�롥P��O��Tm��~����߰i���M��"��t�C7��m�����/��tu�M/>�']w�������){����v��j]��mL�Qq9���=����F�X��ގ?���"����R�smJ�Wwt6Ϻ�{kz;x7G���؀�N����N\���j�a(
݊�E���p��چ��F���t��J,�� 7�V�(�b��ȗ9EAF�/�������\�jʣ�n��&
�Z�QW�F��;Z݉����GU�SU�+�ڡ����QO����q+L�hM�)
ӈp��^��|��\zwV�Łw�8��(H�<�8�C�� ���r����5�t��w�IOP&e����1�d��s�2��;�LQ�)V��Į���(m� �ԡ6n~��9æ9=)Q`�'S�G�G)�
�������<�|�q�v'D��t�Z�(���0�!����ረ�n�Xy4ES|��G[U{T��,����<�O��	����U�����d�xQ\�^�O"/Ua'�,�J���3Lbׁ���X���K��(]���F_ĝ���solS����)�����Ā�(�s��	�AŨ�4�F���T=Cã��o�#EIP�a������Wx���3��T�p�vҕ����G,>�,�3� 	��N��g�r�Q�QF��8�ˉ��`������Љ1?7G� ��M�c��E�����?j��C���5h�Q�����dSkz@�O^ ���-��U@h�Sq�m��q��i
�$�ㅯ u������m�Z�!����{��ď��Y���^<��:�*�wzU�W[;B���a�p�(��*���p_UH�g]k����ug�r<� �c$�,T������Д#�؀���� �R}_+�-W�x����O�����_:��5�HY�eI��<�Y�S�"�����LE�=�D���뵮���᮷%h�lĝ	+g�єdQ���r nk�ա��vЌ�=�ױiZ�����7ኆ��8�LrWn��`ᇅ����k	�i:����4 ��b�F�0�=!�5�D���}��U����-AKMxPi%s�g^�k�����T�xU��".�8,9���e���KaU�VS��l�`����lj�P��H���$�5�;J)NS���˅�ed!�~�`�|J��P�}��6�r�^�:�}��ic���1��U���D������x����-�}�Z0�;�۽B��}/��Gp3�
�Pݸ�w7�u���|�ک��̽���}UеVC���˿�]�g�MR���hk��/�P"���
<�c��9�0��F\-4%��+�@2��W����f���������hp�X�eB;dA�� M��{<'H�	gqBi|D7xs-�߂]�Ǡ�Ҵ%���?�gk!8��It,���2�O��3 tuV�A �f����Y2�0�s�K�TR��i���rщCk��bb�m�]��򪸠D�8�3H=j��V����X ��7��U���$����,qu�~P��T�	��<��9�k�b�7xSb� ���[A�4�AW���W���.M� k��Y8��]D]���Ȕ�F��M���#K��<J�	$�\EZ�+U�hH�"����I�W�t��N�*�[���Q\��p��Z�A���j�Q`�� ���%`m� �R��(�!��,FG���"
3$��=}d!�W�G����{�y�D�����Ҩ:��� �G5����oܕ����?�J��B9�D]��\ ��~e=f.�H?����(t�D�Z�JyI��fI�� Ǝ�2T�כ��+f5H��>����oGS&�:�����V��o��fE�%�<h�i�*�����Z��>���r�.N�����H��cd�
���x2��jt���̉�ktX���\�Ra�P��aJi��!!� ���7^qA�Y�1*:��k��	p5`�p��Y�
�����WPk`��xi!�n�͎��:!��.�m0Jq�[���ãc�s��hY�B����\fYru�ly�W&M+!���\���YU��/�U��u���G�`t!4�v�~��_z��R^Tƙ�Q��� ��~򑻤b��"��p�LkZ�A�v�|*��zv*�=;�Iܱ�b�G92����bc�ӳG����0y��⮯�Sćϟ��F{?��pu'vO�5d��2Q�pf��ZUL	��ӧp�[�hւ��� �� H6D*���Ck�R[�[�4x�m?�;ZZ��ќQG��J�ڱ��`�
�g�F��bKņ<��{v�AZ{�}�k _+���J�hٰ"�qs��*��z��~%�4e��<E�D^}��k�E���X����o�	Rձ�����E�1J��nP3�=�D�>ߦN��)�2��=N�˶�rW?c� �9HLBâǡ}�G�|��8j�[�QE~���R��k�k���8�ݸt�i%+��t(i��j&�u��Nm~���[z/ߖ}B�4�#D��e��*��Ӿ��(����PuX�!{+�d�1�RyQ�L���X\p�����*,c]$���,� �_D��`{��.��_��=�7�z�����!�k$�&=�|���b��%>�h���@�g�:��(���?Ytݓ.:i��� C�n��EL������rQ�W-KY� ��&�/�K4��yO�?���K��.�ڥ[�4��BP�\�L�w������f���j�#��#���ͼk����tL�S�e�h�^�Sѫ�ʋ�>}��2N�zI�@���Y���+�Y\�D�Q^���/�,�Jb'�1@yekFHk�ˣ専��R���A-^pu�[�v7V�';l�բ+�%,��K�@a�sAfA�G�A��s�Ј3ը�
G��P���SS-��5������`e+����� �V��E�H	;=OK�g�� ��_�bd(�}�違;�7r�3��d�|U�u�Lt���&퍘�|$�H��}��Ь)��ҔGv���I��R�rY�)�+���8F%�Y6+�Q������8�"�/�b_B*T*J�(L�^�Z
��ƙ�N�d�����e�����.hs��W�I��Q�Q槱$��0>�\�΃����	�"qX>�ޝ!��^�p��>]5����L2Z�f�P�XE��K�t�y��Ű!v<��ȹ�i;sbnv;��3D=O���x�&,5����U7�"k��<6ؘ�e�7ֆ�x��m`Y[k�m`XyY4�2��3/V�H��S�ܨ�l��Y�AOG��<N�-(����˳�4O�:R4O�&݇��r�u��&/���]8�G>ӄa���0IxJ�4�Aly��2�;K�C�����x,���f�F���A�8�$K���^yJ!�㙦_����W�F#L�/k��z��<�+,�Eu���'#γ�^�f3gi��8E�� B��;�.\S�m���YHr����,!�
�y�k6���(���
�2/+���RzY"/̊ �"

�i���b3`��`s����NĻ�`˔�yh�����&y�+ᇢ�%�������Fb�}
���o&�%�sDؑ���>��E���*���r �۷jIa�U���u/V�[m5�y��O[���x�5�Њ�ˠ{��2�w�"�(ڶ�`�:����aw~�
v/��q��K��� OQ�l=c$���!y<��� �A���f�12̒��2wj��ix@�}�&�+�BI��V<$\�H��AYbNC ��������O(qk&������WSs-��.����&_
d
�M�Ʃ����oE|����cT�(�`LI?�2�V�.�0�2 �  ��s<�qs��|��L �CB�Vp#��v��֑�h��_�¥;ٔ�?���{uZ���&����V�=2�(�� ��L��.��*��^��2/J�܃^��DVyV����	�_d��n&3Ӷ&.��Y��Ϊ#	��2� �a�cX�b?1b9�cs�����yP
�m�G�:��G�ly�9�d��$�4C�V���Y�hQ�D�A�W�i�S9qu{�c�#ze$�����0���\��xd����g7�V��<�|�5���B��8�XH���(�s�-.�{�m7���2�a�c�t}��� 1�)���M�/��ws*T����=�|�t?:s~� ���=�u��D��_�)~ϠfW��1��l��ri��!��P�%Y+���	}~7`�C|��Rf�����P��A�'���(:cS���w3���<rܱDEfT@�DQ�t���hV]�_\�۴�ܝV���8L✏��G�}�Ɠb�L�U�x��<Ý��wi_�5/��,���Q�Z�{}w��d=h�C�������e�}PH/.�܋B%=U���*�WI��ɑ��P|O��G�q����G��p�E~��xH#�;2��xZজ�2͓�����h����w����l[��.Zh=Mr�3�6��ɽ�1� �_{f���Apӹ��f�%x^ͭ
�%����7g��|9�µ�ѩ�_�
�����j~�i��c�l��`�n ��q3�&ȉ���Z�SJP�9��Kw
�?��;Hzs�>��m�I�6j���7�F`����x�1�&u۝��FK��5�?!��f�q��(g��y޶�#3��S���|��P�KyͶdR5l�J1���SC6�D�/�5���)�a�Wu�K;uY�����P�td���<�-8����9fvs� �b@?����4Q����3 �]\��ăp�X_�i��Fȹ�����ߛ9@��|~������>ST      �   �  x�=�M�A�׮S�fX&?�2�HH������Uո����{�9r1�	beɪ���n`;>����Bk U:Ⴔ&�2�Z�$����ՎB�D��V��p�^*b�y�dA���ٳ�nAba�4
ci��ռ��v��{�I�I:�쑞Fe��5��e@=��(�aOP}]��n�^��'{#��Ү����O�Wя��P�J0�{��� �v�B�r�v����(��-,)�*���2��1If�o~J��>N��z�y�}4n�H�>S����}��׉"|ۮp�J��$�t�D �r��"p�0$3�6)ZiG3?R�1�z4x�<c��{�O����"�U<p.�-�چ/;�]�5��W�SC�8z<J��q��NE�{>���C�&;�>H�<��4Bl�i�,X��xj�����9��$ׂ      �      x���q�wt����
1C�]��b���� b��      �   �   x���K�@Eן�õ:F��TB�^$V���������}(�i\.�͹κ���e��e`UW6l	aI٨m�a�ۇ�S��e�*A۞*�9�mz��#s�5�~h�/M1�( 
�ns�w� ���9�^:-��jM�G�yLZ��.f���q��(��0��Y�>�zI�:�{O�!S����cE��bY�      �   $   x�3���q�wt����2�pB�]�����1z\\\ �l
      �   &  x�eV�r�]_vY!���R�c[5���T�A7n�5= Z�G��.K�X��L'U���D��u�.t�Ӯ���,�FI�4��,o
2����q
{���L;�Ң�)m�6#���x��^�ɋ_�Q�,}p��'��S8X�S��U72��L���	�yщ��i,�g�=������Tey�P�7iC7�IM=k�Ӆګ�1��U�W;�T�y��Z^�h�;Rw^��X|�n�v�t��?���ar�[|������ڨ��L�h�G�����e�+�Y&Y��Z�.э,�\M7$�.i}M|�j�0e�eV�AMA�`z�y4�	b�pR嬝h�����:�f�@�����E�&�`�ųF�۠vF��m�SZ�(��/7}o�)�{~1,,V���hJ��|�p�����xp$C�*���Y��0�8FH�	 �$-�Z�Ioxqrd��8����&u�&i3�H'��D�u�K���y=ԅ�*����T�Nh��7��"iS�����ٺG����ZH�y��}8�4+�J�3��zУi{��9u���4� _3�d��?}V�ل�v�Y��#$f�*\�)˴BQ!بD5G�T 
^l,nc&l�8ʋ���aq�d'q0�����t���}����2i�P#���x>ȴ?CW�u�q�e��E�T�- ٴC�E_�Ee�\�[�)�C|R�Ҥ)!�4�S��d	,��7j�02���쎧U��4��{hj�& �~�g��v�]-4O,���5��T5��p��wX�b	?r��fy��2�l��r,���+a��L�j�OR�S���>[0eѰ��zs��NO���R,������E����k�&yJm�	}�v�4�0Dg���-2�
$�Yp�e���'}�z0��Jlg���C���&���"�i�:	�e�YS!�P8��dp�A���I��@f8R^��˷5�X$�SJ �i�lR:� ��1*��c����~��?,��ŉv�v��c`z��j��*��Y����R6y�Ȧ��2kVU��Ga��r3~���Ƒڪ)**�l!�;H�;�
>��>�ݭ�X��0�Լ�"ׂ3/��T�R)�;gt̚i���la�c<(+)˫��o�q6�:X�fz��I��z�N"5�Q�����6�nqX�`dw�ܽ.�����B���M��?5�LX+Y4M&�,)e��,�M�i��kڴ��)-�_���f��+d=_�N�.�5�@�$�]�vY{�^�>Fx�^�
��:����4I�0ŬM�W7�� =#�WG�H'�EXn�4܏#���_��>�ߍ�[ll�3��3@�|�yr7i;E֑����Tӥ��M��qE�ʮ�3�]wU�Zt��S@9���j�M>�W����2U�D�90����Sq�ͽ�b?���!R$���Z5C��i�rc����M�T�nY�i��-:- ����Zo���̓�0Lϫ=ߢ�2J W���L2�����g�r��ߐZķ;��d{��ṧ������y��-Ҝӡ�C�jYdy/ۮc�����:S��E�"�����ކ��OȜZ�/FGE����� ��q���G�=���_������$�&K˒�>-.��u��2٩8��9B�b��Հ����|{ ��ጬ��g��;�RT}ہ��=�
ͭl�8�9�R'u[����a(P0���@W�^�H�u�̲ �ȍ��9]W1zn۫N*٨C���D� O�E�]�&�7�ǋ_��Y���������>��X)򑊲�����z�`C $��r���M(�^� ��Q�O�x"��4��#�q^��w�'�~��w��H/��      �   �   x��K�0@�3��hĿK%��&D\�i�i1S$�H���Iܾ���@��c�P����CK�6�q	�,�T�j�U=]�i�!
�Y��9n�7Vj���d��[(�E��.��W�,m|;��l-��Qa��q��V�Yr�Dp�Zˊ�N��?��q��9"� �E=O      �   1  x�mTKn�6]�O�]���ԯ�k{�� �a#�f6%��f,�
I�ݾ��s��XJN7b5�@	|�އl?܉+�3Z�'i���o� 6?�\�v��L6?��޽�7�w֍F���Y�hr�Dq��4zq?�h4<��1�"o�wB�q�|$UQH(eY+��TI�&R�,o�l��\�u#yO��ϼ.$?|�sV��~��+�x��'qg쓱�@�G�E��M�A�ٞ�#�i�h��3׼T���X��U6Y�ګ�<k��HJ�-�'�p7�2�h]�nLc	�w����l���N?L��ǑY���[�{"���H�� �T�|+U��&۬��&�����s u4�&&��֏dZh�w����3>��`�}�"�)�2��J|�hM��=mrɞh�vϦ��dt�D穃;��8��h�{J���^�[�	c�����wF�R�_z˟��{t�L�NZ��R���՚N�����]6�];iRB�~�q��l���fa�嬋�Y/i����~5큄��&��^+�sJ;�M����ʅtI��W,�&UM�YTp$ܞ�7�>G׎'6����fD�Ȱ�����]�Xs�$>�=���F��p��P�/�̱θ^��ӳ�d�Z��i
ֱ:R��f���n�ï�4[����Պ0O�pH�L�`L;s��aC����\ǁ>�6���������PZ׎�^5Yz��9]2p?�-y���t�l_ɷh�ķ��C�9R׬U!7����e����\A�f��xε"׉�rre���9O/'y��YnqY��K�C�7��R�M[�]2�������I��      �   �  x�]S]n�6~���P!Kr����O�Ea���(�eB�%�$G˟x���X�.Ѝ�E$r��Y�ކ	��>[�%� O��Y�ɖCc3��0�LniFJG�������D!��6�z�D��A�cZ�v}�j��W]�׷������t��!ϔ�0P��N�s��p8ةD|v�6�9e�+���ؕ9�V�iT�}q�ج"e��#}W���g���]QY�~�݅J�m�/X&1�jqū����6/`������)@�;a&%�I濙>��^�w�[���b����Df�S�����H~�	��?���x���$�G��BƢ�ՀH#l ����V��^��nWp-�%��䤶RqC^�ca@�VIj,j$q�f�r��e3z��rUT�Ġ�V�M��!b035[����ὤ�h5';�Z"O=|+lg�Q�K���?�6'J�I�'J�����J�R���7;&0�����1�V�K�k��f.��+Bzk�!�K���s�t�V�{x'쭁\��3��z��{dyQ��;����:SV��4��g�� I}��hH��Fn�p)�;�[ ,��M�C��#�B#A#��F.��A�I�'逐�cR_�z��g/G%����k=��a���V���Vh=���&,�k"��.��|�mIYlx���cEI�Rd؋�Y��`R�+�[��t�W��������fn      �   �   x��9�@@��>�\ �}i	H�@G�d��R��������N�^]Xe�5�44&1�ԉq w���&��1<U7p�r[\�a�d��Pg�w�(J���?l�8��ES���fUn�%4�F�G�q�Lm�'H���Z¹X�(�Z ��N6�      �   �  x��W�r�8]�_���L�)�)R�Ɏӝ����TR�U*��(X$� �,�kz7��l��cs@ʲ�t�6�q�LK��܃sϽH[���I	RrU3�YG��+�5�z�X,X7�s���1�+�p��z�Z��}����\ӂ��UFX|y/��������4'�ܲ�9W/ӂ�2m%�f!Z�!��O2���5oNNj�$�Gs=�<��F'�o���Ҟ�Z�]a���Uܜٙ���3�m8���<I�����7=΃����4�\��][� �EC�4��$��� (0�"�G!ɭu�b?��$�2r��El�YtBS��6Ǡ��V��&\2	X��@\עlҊ5�i��B�� _;&��o��H�M����Jp9��(v;�m����`��A���8��<�Dq�fe�'O���-��s+�JH�in�L�@_G����u-�9$A��Iƣ����� ԣ,���-�k&�+$����4����,4��ל�?%Ӻ�>B3�cg�(�[22��I�DƝ2�\^�zX�8K�t��j��3�����P�ms�;k��=�� �l��'�����^�@?	-VN�v��w|��'-���u�ا��Xö�v;�ya���0$�}(l�]$��eG`���(���������t����/����v6g��B��9�!�W���Z��Q����\t ޺D�kA��C+�di�d����c���iȒr�����j{q�W����^ֽ���0#��$��C�j!G�Pz8� �0�x|��ׂ{�W*�8�Fh��W0�����]iV�>5َ*۳�;|յ�?C��$Hb2Ecr��К�9o��b��>��T?p&饒Nǖ\p]z�#ǆ���-z
���Jr�+%U#
z�Kxs	/՜L�^�\7ц�E#�W�I���5���N�A�c��A�,��p�zgM�U����i! ���$��RRY�Y�	�Y�	$�pg�U�m��@�h|t��%�S��E�
ѳe�V�B�@�Y)h�2�|c�!��,z.2�(ҨW����������������lv�y�Nf=	/���=/��2#�(�5�ec�O�(!��UQZwk��0���Bu��O�fah_i&�A��Ҋ����L)�r�`�*Kn+D��n�^3�'ָz�K�3 sE�#Y2��I����f����sؽՀɔ3�a�Wʫ�*�� ��+����k���w'Q�Ϯ.��;���(w�C.6j��ާq��*{IMH#�ZW��(��/���k95|�9F��Lã@,��lѠ��l��e�%B�����X��>�(��>���Y���n�����2���uMѴ��Jl�tH�8~�@�|?��ޣ<Ȫ鹑^�9�4�Z`��y\z;�x��oT���tFtK<;m�qXG���z�U�!x�.��j�L�9Pnp�D�e�|���LP>b'D% ��q^´�S��r��w�ZQ��^���*OϘ.�t�iY%�7��_C��2A�G
z�U���>׏��#I.������c^�FuFHh:���KV��;�����A��H4U�G�jf�_>��, ��"�����^���(���l[��a�rO���(h�)��i���g���N�X	�����k���t�t?�	�y����������>{wEd��,��c wAQHVuݔZB\��:I���'Ȓ�F��}��xA[a
��hO��3�`H'��M�1E/ߊ����
��9�&Q������S����ˮPŚ�z�:X�M��lQG��z"�(^��I�3:��
�Ċ�p���T]�.Y#"�����}y����O��h��s��=f��h��]h<
3�w�6L;v��j�)Yޗ���?�}�� �� o���jW�o���d��߅�|��S.��x�>��mQ��|t�WJ/!+�nպ�)���)��
����~p���۝�s���������-�����n{������ږ A`��Y�� ��\�f!ÿ �;����c�&�������.�W�����	���A�^|���xp���������\�J�R������|T+Fa6w#�#HF1�_	n���BW�u(T}�`��&�<~�90�n��zJr�������K��z�}����;�7�݀�����&vܐ�'���v����FGGG���*�      �      x������ � �     