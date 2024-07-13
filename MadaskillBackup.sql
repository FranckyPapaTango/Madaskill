--
-- PostgreSQL database dump
--

-- Dumped from database version 11.16
-- Dumped by pg_dump version 11.16

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: application_user; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.application_user (
    id bigint NOT NULL,
    phone_number character varying(255) NOT NULL,
    email character varying(255),
    libelle_adresse character varying(255),
    ville_town character varying(255),
    pays_country character varying(255) NOT NULL,
    internal_user_id bigint
);


ALTER TABLE public.application_user OWNER TO root;

--
-- Name: commande; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.commande (
    id bigint NOT NULL,
    date_commande timestamp without time zone NOT NULL,
    tva double precision NOT NULL,
    taxes_totales double precision NOT NULL,
    montant_amount_ttc double precision NOT NULL,
    is_payed_is_facture boolean NOT NULL
);


ALTER TABLE public.commande OWNER TO root;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.customer (
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


ALTER TABLE public.customer OWNER TO root;

--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.databasechangelog (
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


ALTER TABLE public.databasechangelog OWNER TO root;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO root;

--
-- Name: invoice; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.invoice (
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


ALTER TABLE public.invoice OWNER TO root;

--
-- Name: invoice_settings; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.invoice_settings (
    id bigint NOT NULL,
    custom_fields character varying(255),
    default_payment_method character varying(255),
    footer character varying(255),
    rendering_options character varying(255)
);


ALTER TABLE public.invoice_settings OWNER TO root;

--
-- Name: jhi_authority; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.jhi_authority (
    name character varying(50) NOT NULL
);


ALTER TABLE public.jhi_authority OWNER TO root;

--
-- Name: jhi_user; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.jhi_user (
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


ALTER TABLE public.jhi_user OWNER TO root;

--
-- Name: jhi_user_authority; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.jhi_user_authority (
    user_id bigint NOT NULL,
    authority_name character varying(50) NOT NULL
);


ALTER TABLE public.jhi_user_authority OWNER TO root;

--
-- Name: line_item; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.line_item (
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


ALTER TABLE public.line_item OWNER TO root;

--
-- Name: metadata; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.metadata (
    id bigint NOT NULL,
    order_id character varying(255)
);


ALTER TABLE public.metadata OWNER TO root;

--
-- Name: photo; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.photo (
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


ALTER TABLE public.photo OWNER TO root;

--
-- Name: post; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.post (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    body character varying(255),
    categorie character varying(255),
    resumee character varying(255),
    auteur character varying(255),
    date_persistence timestamp without time zone NOT NULL,
    user_id bigint
);


ALTER TABLE public.post OWNER TO root;

--
-- Name: preferred_locales; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.preferred_locales (
    id bigint NOT NULL,
    preferred_locales character varying(255)
);


ALTER TABLE public.preferred_locales OWNER TO root;

--
-- Name: product; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.product (
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


ALTER TABLE public.product OWNER TO root;

--
-- Name: rel_customer__preferred_locales; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.rel_customer__preferred_locales (
    preferred_locales_id bigint NOT NULL,
    customer_id character varying(255) NOT NULL
);


ALTER TABLE public.rel_customer__preferred_locales OWNER TO root;

--
-- Name: sequence_generator; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.sequence_generator
    START WITH 1050
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sequence_generator OWNER TO root;

--
-- Data for Name: application_user; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.application_user (id, phone_number, email, libelle_adresse, ville_town, pays_country, internal_user_id) FROM stdin;
1	Practical HDD networks	Jeanne_Hubert@gmail.com	array and Rhône-Alpes	quantify Designer	d'Azur CSS benchmark	\N
2	Bacon Self-enabling	Amour.Muller@gmail.com	Shoes Small solutions	open-source la JSON	plug-and-play Switchable Movies	\N
3	Customer-focused	Thomas98@hotmail.fr	Jordanian	c Bedfordshire	overriding parsing	\N
4	Som	Hardouin_Gerard46@gmail.com	generating deposit	Ergonomic	Koweït	\N
5	invoice cutting-edge	Nicole.Bonnet80@hotmail.fr	programming bypass	Shirt Object-based Shirt	unleash	\N
6	Account Midi-Pyrénées up	Adolphe.Joly50@gmail.com	Handmade	b Won	Bike wireless a	\N
7	invoice e-markets	Lydie_Fabre96@yahoo.fr	Account SSL	Richelieu foreground	Dollar	\N
8	enable pixel	Solange_Rey@hotmail.fr	efficient program	global	Euro	\N
9	Wooden 24	Almire67@yahoo.fr	task-force	Plastic pixel	République a	\N
10	indexing bypass	Coraline3@yahoo.fr	system-worthy Chips	transform Bedfordshire circuit	generate Account d'Azur	\N
\.


--
-- Data for Name: commande; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.commande (id, date_commande, tva, taxes_totales, montant_amount_ttc, is_payed_is_facture) FROM stdin;
1	2023-10-23 23:34:58	80404	16215	55564	t
2	2023-10-24 01:51:54	72578	29683	83813	t
3	2023-10-23 20:00:14	77157	97545	42697	f
4	2023-10-23 20:35:20	89991	62565	22787	t
5	2023-10-23 10:49:32	80971	59850	83566	f
6	2023-10-23 18:42:36	22542	67440	74057	t
7	2023-10-24 03:48:50	42295	9409	83577	t
8	2023-10-24 04:53:42	56314	46284	81139	f
9	2023-10-24 04:03:15	55526	27832	19873	t
10	2023-10-24 06:36:34	95500	1878	94553	f
\.


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.customer (id, object, address, balance, created, currency, default_source, delinquent, description, discount, email, invoice_prefix, livemode, name, next_invoice_sequence, phone, shipping, tax_exempt, test_clock, invoice_settings_id, metadata_id) FROM stdin;
2fd91947-cb33-4a97-bf12-3e45c649c026	a Producteur Inde	Rubber Tchad	24837	27422	synthesize a	online	f	Administrateur Progressive extranet	69260	Arabelle.Simon@hotmail.fr	Saint-Honoré Directeur	f	protocol	83950	+33 307724207	Kids	Peso	2023-10-23 12:01:30	\N	\N
cb4ac962-03e2-4c6a-9ae0-2ae79e98c71b	FTP Concrete	bypass Hat Berkshire	16417	57019	AGP	connect	f	Horizontal generating Bac	93196	Yolande_Lefevre@hotmail.fr	Auto	f	technologies	31581	0224960494	overriding Jordan multi-tasking	Account	2023-10-23 21:27:20	\N	\N
473d4eb5-2034-4c10-bc4e-90d3f7cbdd09	Open-source parsing Presbourg	c New	81280	78576	e-business Montmorency	Keyboard b scale	f	Corse Sausages user	83769	Gaston_Legall78@yahoo.fr	Rhône-Alpes	f	deliverables	88410	+33 580113775	Haute-Normandie payment fuchsia	open-source	2023-10-23 14:29:48	\N	\N
9744b08b-ef87-421a-ad77-21d09fc7ca37	User compress user	Vaugirard Franche-Comté array	58540	92346	optimize	harness	t	payment uniform quantify	13763	Flicie_Pons86@gmail.com	parsing Fantastic Bedfordshire	t	Specialiste	53556	0779070498	Beauty Awesome Upgradable	Consultant backing	2023-10-24 02:46:40	\N	\N
09884f76-307f-424e-b3ae-a677d98b9cea	olive parse	hacking embrace	52040	29820	Agent	Ball monitor	f	payment navigating	96642	Fulbert.Poirier38@gmail.com	PCI	t	Cambridgeshire Unbranded	32909	+33 613635665	Du hack	payment	2023-10-23 11:30:53	\N	\N
8b8e051d-f057-4193-87ad-947890b280e6	Stagiaire c Directeur	Tasty	37197	6343	paradigms actuating Berkshire	c multi-state Baby	t	Ergonomic	31486	Adelphe.Durand35@hotmail.fr	Shoes	t	mission-critical Designer a	48357	0763419067	integrated Bedfordshire	core	2023-10-24 05:24:39	\N	\N
25382d75-13d6-4e70-aec8-399adf85a11b	a	Mozambique Unbranded programming	10612	6668	Games feed Stagiaire	Tilsitt virtual Small	f	revolutionary	29077	Sylvain7@gmail.com	Inde Credit	t	Unbranded	74821	+33 566407848	Steel	Buckinghamshire	2023-10-23 18:19:05	\N	\N
816bc64f-eb13-4d11-a53b-5410800bcd47	deposit	teal Shoes synthesize	43434	9683	user-centric Cambridgeshire user-facing	overriding Bike	f	transmitting content-based a	66449	Genevive.Lefebvre@hotmail.fr	compress calculating b	f	overriding Garden b	64172	+33 347018304	B2B	Jewelery de Implemented	2023-10-23 22:51:57	\N	\N
fc9a4e1e-e850-4c62-a804-3b4d2c0ebba4	application Market	Table	43067	84941	Gorgeous Exclusive Soft	Cotton Architecte functionalities	t	transform	25305	Cllie.Adam@yahoo.fr	Frozen brand Sum	t	optical	66867	+33 299084828	web-enabled Haute-Normandie website	c auxiliary architecture	2023-10-23 17:42:00	\N	\N
15cd5430-0946-470b-851b-6fca0d47c26f	RSS	Fantastic	59671	40017	Coordinateur copy	connecting	t	invoice	88538	Zacharie40@gmail.com	Albanie a	f	Incredible Steel Plastic	34656	+33 206913961	transmitting	hub	2023-10-23 17:50:31	\N	\N
\.


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
00000000000000	jhipster	config/liquibase/changelog/00000000000000_initial_schema.xml	2024-01-16 10:17:17.362649	1	EXECUTED	8:b8c27d9dc8db18b5de87cdb8c38a416b	createSequence sequenceName=sequence_generator		\N	4.15.0	\N	\N	5396637166
00000000000001	jhipster	config/liquibase/changelog/00000000000000_initial_schema.xml	2024-01-16 10:17:17.476411	2	EXECUTED	8:86fdbf51ba67c109ddc6c227ba80c4ee	createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...		\N	4.15.0	\N	\N	5396637166
20231024102150-1	jhipster	config/liquibase/changelog/20231024102150_added_entity_Post.xml	2024-01-16 10:17:17.491887	3	EXECUTED	8:d78d557ee904375a5e508abadfe1747a	createTable tableName=post; dropDefaultValue columnName=date_persistence, tableName=post		\N	4.15.0	\N	\N	5396637166
20231024102150-1-data	jhipster	config/liquibase/changelog/20231024102150_added_entity_Post.xml	2024-01-16 10:17:17.509064	4	EXECUTED	8:e5e7d4ce153e3af568a57fb661046fb3	loadData tableName=post		\N	4.15.0	faker	\N	5396637166
20231024102151-1	jhipster	config/liquibase/changelog/20231024102151_added_entity_Product.xml	2024-01-16 10:17:17.528173	5	EXECUTED	8:49f0c5a9ce2fa08649e707634138f666	createTable tableName=product		\N	4.15.0	\N	\N	5396637166
20231024102151-1-data	jhipster	config/liquibase/changelog/20231024102151_added_entity_Product.xml	2024-01-16 10:17:17.563323	6	EXECUTED	8:05866bfdc6815d104d5077221e7794c2	loadData tableName=product		\N	4.15.0	faker	\N	5396637166
20231024102152-1	jhipster	config/liquibase/changelog/20231024102152_added_entity_ApplicationUser.xml	2024-01-16 10:17:17.576579	7	EXECUTED	8:31e7a07032afbdf9a3b40286a68ee618	createTable tableName=application_user		\N	4.15.0	\N	\N	5396637166
20231024102152-1-data	jhipster	config/liquibase/changelog/20231024102152_added_entity_ApplicationUser.xml	2024-01-16 10:17:17.586812	8	EXECUTED	8:2eb892727f6036a2345bdc9f9a0457a9	loadData tableName=application_user		\N	4.15.0	faker	\N	5396637166
20231024102153-1	jhipster	config/liquibase/changelog/20231024102153_added_entity_Customer.xml	2024-01-16 10:17:17.606924	9	EXECUTED	8:b9af31c24b06b02e9cd2250ac22ebe66	createTable tableName=customer; dropDefaultValue columnName=test_clock, tableName=customer		\N	4.15.0	\N	\N	5396637166
20231024102153-1-relations	jhipster	config/liquibase/changelog/20231024102153_added_entity_Customer.xml	2024-01-16 10:17:17.614733	10	EXECUTED	8:799d44a22ef6d05e3fb9aa95b541051b	createTable tableName=rel_customer__preferred_locales; addPrimaryKey tableName=rel_customer__preferred_locales		\N	4.15.0	\N	\N	5396637166
20231024102153-1-data	jhipster	config/liquibase/changelog/20231024102153_added_entity_Customer.xml	2024-01-16 10:17:17.631702	11	EXECUTED	8:e73750ebce720e154211e78c4bd089da	loadData tableName=customer		\N	4.15.0	faker	\N	5396637166
20231024102154-1	jhipster	config/liquibase/changelog/20231024102154_added_entity_InvoiceSettings.xml	2024-01-16 10:17:17.64321	12	EXECUTED	8:74c0a5c54edb0254995a14707e48c992	createTable tableName=invoice_settings		\N	4.15.0	\N	\N	5396637166
20231024102154-1-data	jhipster	config/liquibase/changelog/20231024102154_added_entity_InvoiceSettings.xml	2024-01-16 10:17:17.654879	13	EXECUTED	8:3ac028fe4968c3607e1a3cf714250464	loadData tableName=invoice_settings		\N	4.15.0	faker	\N	5396637166
20231024102155-1	jhipster	config/liquibase/changelog/20231024102155_added_entity_Metadata.xml	2024-01-16 10:17:17.663348	14	EXECUTED	8:fc1ce052d8c5e1f9100df1033a06b684	createTable tableName=metadata		\N	4.15.0	\N	\N	5396637166
20231024102155-1-data	jhipster	config/liquibase/changelog/20231024102155_added_entity_Metadata.xml	2024-01-16 10:17:17.672458	15	EXECUTED	8:09d2554ad8b1ae0588ea600ba5da88c2	loadData tableName=metadata		\N	4.15.0	faker	\N	5396637166
20231024102156-1	jhipster	config/liquibase/changelog/20231024102156_added_entity_PreferredLocales.xml	2024-01-16 10:17:17.681923	16	EXECUTED	8:51cb1e551e906440cba0677133b23801	createTable tableName=preferred_locales		\N	4.15.0	\N	\N	5396637166
20231024102156-1-data	jhipster	config/liquibase/changelog/20231024102156_added_entity_PreferredLocales.xml	2024-01-16 10:17:17.691829	17	EXECUTED	8:2058a1dcc43cc038f8659424017a94d5	loadData tableName=preferred_locales		\N	4.15.0	faker	\N	5396637166
20231024102157-1	jhipster	config/liquibase/changelog/20231024102157_added_entity_Commande.xml	2024-01-16 10:17:17.701469	18	EXECUTED	8:3e025d36a11d011363eb525431fd8320	createTable tableName=commande; dropDefaultValue columnName=date_commande, tableName=commande		\N	4.15.0	\N	\N	5396637166
20231024102157-1-data	jhipster	config/liquibase/changelog/20231024102157_added_entity_Commande.xml	2024-01-16 10:17:17.711845	19	EXECUTED	8:323f47bb936c8fa31b162c71f4da05cc	loadData tableName=commande		\N	4.15.0	faker	\N	5396637166
20231024102158-1	jhipster	config/liquibase/changelog/20231024102158_added_entity_Photo.xml	2024-01-16 10:17:17.726501	20	EXECUTED	8:e45283183a8afcca62dfee98dd8e0f05	createTable tableName=photo; dropDefaultValue columnName=taken, tableName=photo; dropDefaultValue columnName=uploaded, tableName=photo		\N	4.15.0	\N	\N	5396637166
20231024102158-1-data	jhipster	config/liquibase/changelog/20231024102158_added_entity_Photo.xml	2024-01-16 10:17:17.739382	21	EXECUTED	8:2f58b31d4fb549b624e76487b882fc51	loadData tableName=photo		\N	4.15.0	faker	\N	5396637166
20231024102159-1	jhipster	config/liquibase/changelog/20231024102159_added_entity_Invoice.xml	2024-01-16 10:17:17.758336	22	EXECUTED	8:f280abd08746f3420725e35b7b2445aa	createTable tableName=invoice		\N	4.15.0	\N	\N	5396637166
20231024102159-1-data	jhipster	config/liquibase/changelog/20231024102159_added_entity_Invoice.xml	2024-01-16 10:17:17.777709	23	EXECUTED	8:ad6a2b95444629cda0f9c926b197d66a	loadData tableName=invoice		\N	4.15.0	faker	\N	5396637166
20231024102200-1	jhipster	config/liquibase/changelog/20231024102200_added_entity_LineItem.xml	2024-01-16 10:17:17.793277	24	EXECUTED	8:49c5b96ff5eb45d03eec5661fe1957d7	createTable tableName=line_item		\N	4.15.0	\N	\N	5396637166
20231024102200-1-data	jhipster	config/liquibase/changelog/20231024102200_added_entity_LineItem.xml	2024-01-16 10:17:17.803	25	EXECUTED	8:faa5c75c983bc6c8377df29b8ce14104	loadData tableName=line_item		\N	4.15.0	faker	\N	5396637166
20231024102150-2	jhipster	config/liquibase/changelog/20231024102150_added_entity_constraints_Post.xml	2024-01-16 10:17:17.807945	26	EXECUTED	8:21d76f563d1fb8e08c84b80533d0fe37	addForeignKeyConstraint baseTableName=post, constraintName=fk_post__user_id, referencedTableName=application_user		\N	4.15.0	\N	\N	5396637166
20231024102151-2	jhipster	config/liquibase/changelog/20231024102151_added_entity_constraints_Product.xml	2024-01-16 10:17:17.812488	27	EXECUTED	8:3e49da6e08180551f915fabb910d1ab4	addForeignKeyConstraint baseTableName=product, constraintName=fk_product__user_id, referencedTableName=application_user		\N	4.15.0	\N	\N	5396637166
20231024102152-2	jhipster	config/liquibase/changelog/20231024102152_added_entity_constraints_ApplicationUser.xml	2024-01-16 10:17:17.819156	28	EXECUTED	8:8268ecffe1b633f1548185b8cadd976a	addForeignKeyConstraint baseTableName=application_user, constraintName=fk_application_user__internal_user_id, referencedTableName=jhi_user		\N	4.15.0	\N	\N	5396637166
20231024102153-2	jhipster	config/liquibase/changelog/20231024102153_added_entity_constraints_Customer.xml	2024-01-16 10:17:17.828753	29	EXECUTED	8:09a583a521049eaad60815ca806f1bf8	addForeignKeyConstraint baseTableName=customer, constraintName=fk_customer__invoice_settings_id, referencedTableName=invoice_settings; addForeignKeyConstraint baseTableName=customer, constraintName=fk_customer__metadata_id, referencedTableName=met...		\N	4.15.0	\N	\N	5396637166
20231024102158-2	jhipster	config/liquibase/changelog/20231024102158_added_entity_constraints_Photo.xml	2024-01-16 10:17:17.836812	30	EXECUTED	8:970cb266e5a1ae5040ae4da89b7a277e	addForeignKeyConstraint baseTableName=photo, constraintName=fk_photo__post_id, referencedTableName=post; addForeignKeyConstraint baseTableName=photo, constraintName=fk_photo__product_id, referencedTableName=product		\N	4.15.0	\N	\N	5396637166
20231024102159-2	jhipster	config/liquibase/changelog/20231024102159_added_entity_constraints_Invoice.xml	2024-01-16 10:17:17.843514	31	EXECUTED	8:dba1530e343f02e69a338e3535361884	addForeignKeyConstraint baseTableName=invoice, constraintName=fk_invoice__commande_id, referencedTableName=commande; addForeignKeyConstraint baseTableName=invoice, constraintName=fk_invoice__customer_id, referencedTableName=customer		\N	4.15.0	\N	\N	5396637166
20231024102200-2	jhipster	config/liquibase/changelog/20231024102200_added_entity_constraints_LineItem.xml	2024-01-16 10:17:17.853009	32	EXECUTED	8:a8d07b23e64c6c2d1b9373d915de520e	addForeignKeyConstraint baseTableName=line_item, constraintName=fk_line_item__product_id, referencedTableName=product; addForeignKeyConstraint baseTableName=line_item, constraintName=fk_line_item__commande_id, referencedTableName=commande		\N	4.15.0	\N	\N	5396637166
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	t	2024-07-05 16:28:36.802104	rafaros-it (172.29.32.1)
\.


--
-- Data for Name: invoice; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.invoice (id, object, account_country, account_name, account_tax_ids, amount_due, amount_paid, amount_remaining, amount_shipping, application, application_fee_amount, attempt_count, attempted, auto_advance, billing_reason, charge, collection_method, created, currency, custom_fields, customer_string_value, customer_address, customer_email, customer_name, customer_phone, customer_shipping, customer_tax_exempt, customer_tax_ids, default_payment_method, default_source, default_tax_rates, description, discount, discounts, due_date, effective_at, ending_balance, footer, from_invoice, hosted_invoice_url, invoice_pdf, last_finalization_error, latest_revision, livemode, metadata, next_payment_attempt, number, on_behalf_of, paid, paid_out_of_band, payment_intent, payment_settings, period_end, period_start, post_payment_credit_notes_amount, pre_payment_credit_notes_amount, quote, receipt_number, rendering, rendering_options, shipping_cost, shipping_details, starting_balance, statement_descriptor, status, status_transitions, subscription, subscription_details, subtotal, subtotal_excluding_tax, tax, test_clock, total, total_discount_amounts, total_excluding_tax, total_tax_amounts, transfer_data, webhooks_delivered_at, commande_id, customer_id) FROM stdin;
a9d244d5-2770-4ef8-b3db-74ac80ccf9cd	SQL	Frozen Berkshire reciprocal	Home Loan Account	Borders Home access	36325	20522	71595	73808	PNG	97683	25244	f	f	b Rand	Account	transmitting EXE Coordinateur	32340	visualize a iterate	Fresh	sticky Awesome a	Cotton a Zadkine	Agent Towels des	Agent	interactive deposit Electronics	bypassing transitional Toys	Auto Toys	Wooden Intuitive applications	pixel	Poitou-Charentes	e-markets Ball tan	Rubber c Loan	plum maroon	Generic User-centric program	5450	84029	41534	eyeballs Toys deposit	Franche-Comté bifurcated Car	Shoes Granite	value-added	Burundi	XSS	t	customer invoice	4374	deploy	Pizza	f	f	Soap generating	a	16398	51030	61415	30892	Poitou-Charentes	feed Assistant	Handcrafted Wooden	online	59282	Specialiste	11730	La Sleek connect	sky COM	deposit input	a bandwidth	Persistent Rustic	89980	99280	Small	web-readiness	73723	red Afghanistan deposit	19331	Granite TCP Bedfordshire	Coordinateur program	91020	\N	\N
95d17eb0-76f6-45c3-8f64-7a3d5682c8de	24/365 Home	quantifying Directeur	Money Market Account	Concrete Unbranded Computers	44809	84226	31345	54711	Cambridgeshire revolutionize	4671	96139	f	t	Outdoors Bedfordshire	Kids intermediate Refined	Sleek	8410	systems	mesh	parsing	leverage c payment	gold	Gorgeous Small	Chicken Franche-Comté paradigms	indexing	evolve	calculating indigo Tuna	silver c mobile	RSS	deploy	b	Grocery Cambridgeshire	maroon portal	25601	50775	71068	hybrid a synergies	Polarised Joubert firewall	Executif vortals	connecting Administrateur	payment	calculate Fresh attitude-oriented	f	Technicien synthesizing	71805	Kip adapter payment	functionalities open-source	f	t	Plastic	Avon Programmable bricks-and-clicks	83491	86674	17935	43879	c deposit Expanded	Frozen Realigned e-markets	Steel Music	Du	69988	Pizza maroon generate	68485	Persevering tolerance	Small	pixel maximize	incentivize	invoice index	10654	82331	incremental bypassing	transmit Francs-Bourgeois Litas	11192	matrices	43528	wireless Public-key	Dauphine	56961	\N	\N
dea29e08-d0e6-4a52-aa36-d1e3b5b753ca	c Borders	Frozen wireless Keyboard	Savings Account	calculating auxiliary extensible	2244	68347	57735	14102	Account	29184	49103	f	f	Account	maroon Buckinghamshire vortals	e-business	90253	innovative index	Electronics Tala	haptic	Grocery Object-based orchestrate	bandwidth-monitored	ivory	Keyboard	Fish	bypass Bedfordshire optical	Superviseur Developpeur	SCSI Loan Beauty	Rhône-Alpes	Fresh	Dollar Yémen integrated	Hat orange	6th reciprocal connect	28309	2012	1179	Rhône-Alpes	Australian bandwidth generate	navigate viral Polarised	Cotton Singapore state	Picardie	Account platforms AI	f	connecting B2B Cambridgeshire	30515	circuit 1080p	Tools bandwidth-monitored Towels	t	t	Haute-Normandie capacity	circuit leverage	40074	89591	73999	96743	c	scale	a Architecte groupware	Clothing generating Baby	49559	de Provence-Alpes-Côte Configurable	27670	invoice harness	Guarani	d'Assas	program	implement Clothing	31270	11215	Salad	Languedoc-Roussillon	77817	Champagne-Ardenne system	51627	green Technicien	solid Picardie	19473	\N	\N
19a4ee1a-ca11-45c6-b92b-996c0d422a81	a Saint-Denis	Stagiaire Architecte Buckinghamshire	Credit Card Account	Sleek utilisation	48586	56798	46908	58574	Small	93634	38216	t	f	Interface TCP	Nord-Pas-de-Calais Concrete dynamic	Unit Buckinghamshire Faubourg	96430	Sleek end-to-end	Total	Équateur	convergence	Generic transmitting maximized	expedite	b	Designer	Towels	Gorgeous Fish	conglomeration Cotton	real-time deposit	Granite	embrace ubiquitous	Loan Sleek capacity	Syrie Israël	16180	31371	20951	Berkshire Wooden	Plastic	Bedfordshire	real-time	expedite	Intelligent eyeballs protocol	t	protocol	42533	green Frozen	Handcrafted Du	f	t	withdrawal Metal Gloves	Outdoors Tools solid	65606	87337	74795	13614	multi-byte radical gold	granular Beauty	engage	Centralized Boétie e-tailers	54005	capacitor compressing	89841	Ingenieur	Intelligent c Pants	connecting	Steel Front-line PNG	Chair a front-end	83334	35595	Steel	Sleek	27116	sky	31677	Tadjikistan Plastic	Maurice teal	32065	\N	\N
e010bc0c-fcda-4c58-9149-567519e8ff23	Incredible incentivize high-level	end-to-end users Berkshire	Home Loan Account	TCP copying Metal	82162	52949	74151	18699	c indexing maroon	32053	39802	t	f	transform HTTP impactful	RAM Swedish Somoni	up approach	83245	Buckinghamshire Plastic Danemark	Account	Helena facilitate	B2B	SQL invoice withdrawal	bluetooth Practical Estonie	relationships	challenge b visionary	Charlemagne	Manager	b platforms out-of-the-box	Bretagne RAM Developpeur	holistic	navigating	deposit Haïti	d'Assas	77216	3897	32199	azure	Bike Outdoors Avon	interface Cambridgeshire	mindshare Tasty	Quality-focused	syndicate Charlemagne Berkshire	t	Interface	88851	Practical	RAM	t	f	Tools	TCP Steel	21329	51261	13692	60034	IB Checking	Dirham digital	Borders	deliver	15373	Chips	97446	Outdoors grid-enabled system	Intuitive bypassing Fully-configurable	c	Buckinghamshire	Licensed FTP Account	3634	48139	Cambridgeshire	orange Cambridgeshire	36936	sensor cohesive Clothing	52137	cyan	Credit program Refined	3978	\N	\N
a3c5eb65-e884-41da-b4c0-3194618ebd40	Keyboard	Avon	Personal Loan Account	transmitter hardware Shoes	71778	61858	50860	77884	neural	30831	47817	t	t	network	website exuding Hat	withdrawal	59313	Gloves	parsing b Égypte	Account Armenian installation	systemic backing regional	backing Books Practical	olive b Total	microchip Generic Movies	Movies Soft	Producteur	Bangladesh value-added Gloves	Steel b	Monsieur-le-Prince even-keeled	c	navigating	convergence	Administrateur invoice	32575	16669	90817	navigate	Industrial navigating Havre	e-tailers Géorgie Ingenieur	mission-critical non-volatile concept	haptic Keyboard Expanded	calculating Buckinghamshire Superviseur	t	Open-architected	92233	transmitter	wireless	t	f	protocol	Account	81694	5518	29069	93083	c Monaco Centre	a Tasty parse	sensor	protocol Producteur recontextualize	30264	Azerbaïdjan Frozen	22696	payment card a	Movies	Front-line	calculating Sleek override	Russian	91979	6436	Fresh cross-platform	Djibouti parsing embrace	24716	Grèce Designer Synchronised	65223	drive Administrateur Aquitaine	parse	82883	\N	\N
33080f60-9f55-42fb-bfc9-5da47b437efe	granular initiatives azure	Producteur digital archive	Investment Account	architectures teal	30438	80752	26315	88387	Frozen connecting	85213	31260	f	t	orchestration	Royaume-Uni	Angola	90698	synergies Realigned	tan Cambridgeshire Self-enabling	back Sports	Baby	Frozen parsing CSS	empowering	payment Cambridgeshire virtual	Grands	synergize Account	optical	connecting Executif cross-media	copy transmit violet	policy Wooden	Polarised monitoring collaboration	Avon Loan	generating	86554	79542	99578	withdrawal	Tuna	Égypte parsing metrics	a	markets	copying	f	Bedfordshire	11253	b Grocery	SDD Refined	f	f	Cotton programming	radical	75719	39948	68819	52533	Synergistic Concrete	Consultant program	de Handcrafted	mobile Superviseur	57597	Agent eyeballs	70105	orange transmitting harness	du bypassing Wooden	client-server des Account	structure Liechtenstein revolutionary	a web Mouse	42011	6425	Streamlined	Frozen multi-byte Superviseur	1744	Pants revolutionary	8983	lavender Car	end-to-end c	49993	\N	\N
aa3bc9cd-b09b-47a3-862b-38b160db41be	state Coordinateur Technicien	payment Awesome a	Credit Card Account	brand Ergonomic Turkish	56516	97425	43295	48032	b protocol Automated	80512	93587	f	t	Franche-Comté c	a	Fresh Steel Open-architected	70760	Ball Practical Dollar	quantify infrastructures c	Licensed extranet Investment	Berkshire	JSON ubiquitous	a Pizza	Computer	Aquitaine	wireless Balanced microchip	Paris	Botswana e-business SMS	IB Home	ability Intuitive	Producteur lavender Pastourelle	application	Car	32232	14195	92995	synergize Concrete	deposit drive Ergonomic	Frozen Sausages	e-services	contextually-based alarm azure	platforms	f	hard Metal d'Alésia	74018	Cotton Dong	red	f	f	XML	Swaziland silver	87208	98018	12736	75332	Saint-Séverin connecting THX	transmitting	a	Gloves magenta	20388	array secured	82915	c Tuna	utilize red FTP	Provence	magenta compelling Operative	Bike GB	48298	51210	matrix Games azure	a	97811	asynchronous c	4242	payment compress	deposit Games	68626	\N	\N
cf9abefe-df88-4679-884d-62d98ccbe598	TCP software	withdrawal matrix Sports	Personal Loan Account	Integrated	42701	49327	190	18545	c	79326	5743	f	f	Fresh plug-and-play b	morph Namibie cyan	Toys system	97863	Rwanda Outdoors IB	integrated Shoes Designer	Unbranded Games a	Rustic	deploy ADP AI	heuristic Vaneau parse	Chicken	object-oriented	bypassing	Producteur Table	payment	payment hack Rhône-Alpes	b program	Ball Sleek ivory	programming	lavender Dinar connect	28639	42357	74678	Dalasi du proactive	connecting Licensed Zimbabwe	green Tomé-et-Principe	Producteur supply-chains	Soap Îles	Ruble	t	even-keeled payment Movies	33271	Buckinghamshire Bulgarie copy	Home b	t	f	XSS	Aquitaine b	97228	75660	69919	20206	indexing hack	a	withdrawal Concrete Intelligent	adapter Ruble	24938	b	86447	b streamline	Avon Executif	backing	Stagiaire	Bulgarie 1080p Agent	53659	73958	Pants équatoriale	back-end	56548	card Midi-Pyrénées	89440	b clear-thinking	Keyboard Re-contextualized	33522	\N	\N
280af1b2-5c39-43a2-abb6-9ad1fd6c1142	neural	deposit b	Investment Account	Directeur	53563	94027	96668	4049	1080p	2980	92682	f	f	Analyste Generic	Checking transmitter purple	Hat	25241	Soap world-class	Picardie	Table Steel	Bangladesh Car	feed Cotton enterprise	a	Car la synergy	override experiences Wooden	24 b e-business	Syrie navigate encompassing	orchestration Nord-Pas-de-Calais primary	Borders Borders la	payment Serbie	indigo niches	Outdoors	turquoise	22807	60209	85352	Maroc synergistic functionalities	Rubber	withdrawal orange national	transition ivory Presbourg	parse	EXE productize	f	violet Cheese	13261	quantify	Card	t	t	Licensed Borders	fuchsia	29702	93936	11089	74480	b Directeur monitor	Paris	CSS	c up invoice	57438	Plastic synthesize Faubourg-Saint-Denis	9964	structure Uruguay	ability Home incubate	indexing deposit	Buckinghamshire pixel Loan	Bike Avon	19850	6480	Towels haptic a	Developpeur Gloves	97075	EXE time-frame THX	88420	Licensed SDD	optimal aggregate cross-platform	65071	\N	\N
\.


--
-- Data for Name: invoice_settings; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.invoice_settings (id, custom_fields, default_payment_method, footer, rendering_options) FROM stdin;
1	Ouzbékistan b	array Barbados programming	Buckinghamshire Avon experiences	application Assistant infomediaries
2	intermediate c	parsing	SAS	COM b
3	Account Phased azure	SSL	transitional auxiliary	SQL b
4	24/7	Granite Soft	Macédoine up Industrial	primary
5	Salad	transmitter target c	pink	viral
6	Cambridgeshire turquoise	Assistant	Zimbabwe c	Île-de-France a
7	Hat Towels b	Loan	ROI Cotton innovate	system Account
8	a monitor	Montorgueil a	Wooden matrix	Handmade b c
9	Baby Proactive	Investment b b	Chips	Ouganda
10	front-end withdrawal	payment middleware	Enhanced Basse-Normandie bypass	Berkshire base
\.


--
-- Data for Name: jhi_authority; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.jhi_authority (name) FROM stdin;
ROLE_ADMIN
ROLE_USER
\.


--
-- Data for Name: jhi_user; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.jhi_user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date) FROM stdin;
1	admin	$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC	Administrator	Administrator	admin@localhost		t	fr	\N	\N	system	\N	\N	system	\N
2	user	$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K	User	User	user@localhost		t	fr	\N	\N	system	\N	\N	system	\N
\.


--
-- Data for Name: jhi_user_authority; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.jhi_user_authority (user_id, authority_name) FROM stdin;
1	ROLE_ADMIN
1	ROLE_USER
2	ROLE_USER
\.


--
-- Data for Name: line_item; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.line_item (id, object, amount, amount_excluding_tax, currency, description, discount_amounts, discountable, discounts, invoice_item, livemode, metadata, period_end, period_start, price, proration, proration_details, quantity, subscription, tax_amounts, tax_rates, type, unit_amount_excluding_tax, product_id, commande_id) FROM stdin;
d4d2d1b8-2e57-478a-a88f-e2384ef2d3a0	synthesizing	61477	19292	supply-chains Royale a	Frozen	Montmorency c scalable	t	Tuna	Savings b Garden	t	bypassing transform	62390	73818	Advanced synthesizing	f	hacking Sausages	51312	auxiliary	backing Home Gorgeous	Bûcherie	robust disintermediate input	Steel	\N	\N
c593aade-0207-4b7d-b0d8-493d48bf05d5	input Loan	19224	35254	Fantastic	explicit Credit maroon	grow streamline	t	lime	Quality-focused	f	Stagiaire	94963	17512	c	f	Account Mexique Tuna	49485	copy Tasty Incredible	de invoice compelling	Vision-oriented Monsieur-le-Prince	Concrete	a	\N	\N
092e308f-3210-4ebb-b1b4-b0337f74db6e	moderator deposit	84091	72467	workforce Borders Investment	parsing bifurcated	SAS array Account	t	index efficient	innovate	t	La withdrawal Granite	76691	85516	Cotton	t	applications Coordinateur	34691	Mouse Avon microchip	Granite	Awesome Fantastic Fundamental	ROI Movies	c	\N	\N
5575be0d-9a8e-4186-940f-389f32f4c456	integrated online Hat	10853	11071	c Haute-Normandie Beauty	Borders homogeneous	unleash	f	experiences	model b	f	collaborative SCSI	86147	68337	model navigating	t	e-markets	92378	up	mindshare Islande Steel	Manager c Unbranded	Lorraine	Account optical	\N	\N
c4d2718a-3def-4eae-89b2-fc74ca92485b	Movies	79031	98240	Tools	flexibility Basse-Normandie	Automotive	f	back-end withdrawal	Pizza Specialiste a	f	niches tangible	9822	24286	Administrateur	f	installation quantify	3656	quantifying Small Plastic	orange	radical calculate	Fresh Directeur	neural hour best-of-breed	\N	\N
690864aa-2b8c-4f65-8350-87675288ea61	system d'Alésia Ball	96846	43459	c Intelligent	indexing input Baby	c a Games	t	SMS matrix	Practical override connect	f	policy	96825	23696	TCP quantifying	t	Intelligent pink	28010	Account	web-readiness Borders	b synergies	multi-byte azure	COM Ball alliance	\N	\N
e286af4f-0eda-4882-8205-61a21d838f13	a	89182	39015	XML Chypre	analyzing Hat	bypass	f	green Frozen	Buckinghamshire	t	capacitor	2896	31011	Metal scale	f	context-sensitive neural	81878	modular	Fantastic SDD invoice	Fiji Enterprise-wide	c	Networked Indonésie Bike	\N	\N
a6a8b12d-80bf-4479-b7c2-dfd7b6714dd4	deposit SCSI	43238	98727	incentivize	Small application e-commerce	recontextualize	f	HDD architectures Soap	Franche-Comté Bike b	t	Bedfordshire	92546	36358	Monsieur-le-Prince invoice Open-source	f	integrated hacking 1080p	19502	b	Stagiaire	Provence-Alpes-Côte paradigms quantifying	Market	Cotton Quality-focused navigating	\N	\N
9413e1fb-f81d-423c-9bbe-de42fec372a4	transform e-commerce	68308	31235	Gorgeous	HDD Bedfordshire	Mouse Kids	f	open-source enhance solution	Generic wireless	t	Oman blue	89700	82155	Pound back-end	t	Object-based	3331	Borders	Multi-tiered Manat	b	Towels	initiative quantifying	\N	\N
46c9b950-2cc0-4de9-998f-e43ea5d0795c	Producteur forecast cross-platform	22208	58014	pink	maroon Directeur	Awesome	f	invoice Cambridgeshire Franc	Fresh	f	Poitou-Charentes Keyboard	2057	77867	input	f	Money	45411	neutral grow c	Nord-Pas-de-Calais	back-end Saussaies	gold Berkshire	Convertible Seamless Intelligent	\N	\N
\.


--
-- Data for Name: metadata; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.metadata (id, order_id) FROM stdin;
1	Persevering payment alarm
2	AGP
3	hierarchy Micronésie
4	c b TCP
5	mobile generate
6	SSL silver Franche-Comté
7	Géorgie c Savings
8	Cambridgeshire
9	Rubber
10	Berkshire face
\.


--
-- Data for Name: photo; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.photo (id, title, link_to_photo_file, description, author, owner, height, width, taken, uploaded, post_id, product_id) FROM stdin;
1	ADP Botswana Directeur	wireless 9(E.U.A.-9)	Frozen Ergonomic Avon	deposit Madagascar Rustic	Saint-Bernard compress	57552	62683	2023-10-23 14:32:06	2023-10-24 08:22:02	\N	\N
2	l'Odéon alarm seize	Frozen clear-thinking a	Franc b	digital challenge	Games eco-centric	46326	31435	2023-10-23 18:36:17	2023-10-23 17:41:55	\N	\N
3	Market	Plastic bottom-line	Rhône-Alpes Plastic programming	COM green	navigate	34557	19736	2023-10-23 23:34:19	2023-10-23 21:44:21	\N	\N
4	Shoes	action-items Armenian	b	Versatile	Shirt dynamic	5420	82124	2023-10-24 02:19:07	2023-10-23 10:42:22	\N	\N
5	bandwidth-monitored	Practical	c Steel	Re-contextualized innovate invoice	Realigned Bedfordshire program	37033	5837	2023-10-23 16:44:17	2023-10-24 06:38:31	\N	\N
6	structure	Industrial	connect Buckinghamshire	c	Libye open-source Argentine	19519	6197	2023-10-23 20:32:38	2023-10-24 07:03:04	\N	\N
7	Buckinghamshire override Auto	Coordinateur primary quantify	PNG Madagascar	back Investment Soap	program a Kids	61813	24240	2023-10-23 21:11:11	2023-10-23 19:56:47	\N	\N
8	blue	Account Cotton	payment b supply-chains	granular motivating Wooden	Buckinghamshire	48145	75987	2023-10-23 21:11:15	2023-10-23 13:37:10	\N	\N
9	deposit	Rubber	pixel deposit Azerbaijanian	interface	Car	52937	59370	2023-10-23 14:16:38	2023-10-24 05:05:43	\N	\N
10	Electronics systemic	quantifying b	matrix	payment	integrate	66021	24812	2023-10-24 09:02:37	2023-10-23 13:56:17	\N	\N
\.


--
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.post (id, title, body, categorie, resumee, auteur, date_persistence, user_id) FROM stdin;
1	Singapore Limousin	Tasty action-items	Hat	help-desk Tools Loan	magenta mission-critical Limousin	2023-10-23 22:56:07	\N
2	synthesizing repurpose	Fully-configurable Analyste	system	solution-oriented explicit Bretagne	index collaborative	2023-10-23 21:34:27	\N
3	Chair	Vaugirard plum withdrawal	Avon	Steel Chicken	navigate generate	2023-10-24 08:37:25	\N
4	Practical Technicien	XSS connecting	clicks-and-mortar Handmade	Specialiste	redefine PCI	2023-10-23 18:04:01	\N
5	initiatives Consultant	optical c	de	Avon du deposit	Cotton Administrateur	2023-10-23 14:30:05	\N
6	Franche-Comté	Enterprise-wide program	quantify Granite	enterprise Awesome	Cheese	2023-10-24 09:31:56	\N
7	Kids	copy	Bedfordshire applications	Poitou-Charentes connecting	frictionless	2023-10-23 22:57:00	\N
8	Ergonomic	turquoise back Diverse	Borders AGP Shoes	calculating a whiteboard	e-commerce c	2023-10-23 22:46:47	\N
9	Vaneau Handcrafted Down-sized	Persistent	Reverse-engineered	Books Venezuela	payment Corse	2023-10-24 05:48:44	\N
10	invoice Directeur microchip	Zloty	Customizable	synthesize zero	Shirt magnetic	2023-10-24 06:52:33	\N
\.


--
-- Data for Name: preferred_locales; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.preferred_locales (id, preferred_locales) FROM stdin;
1	Saint-Denis
2	Cambridgeshire attitude-oriented Unit
3	Executif migration
4	Corse
5	Tuna
6	leverage cutting-edge
7	connecting b Hat
8	approach
9	gold
10	Burundi
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.product (id, object, active, created, default_price, description, livemode, name, shippable, statement_descriptor, tax_code, unit_label, updated, url, sku, title, link_to_generic_photo_file, available_sizes, currency_format, is_free_shipping, price, style, installments, user_id) FROM stdin;
1	pltv	f	98311	17	Plateau en verre réchauffe plat, forme ellyptique	f	Computer c deposit	f	withdrawal THX b	Metal	infomediaries partnerships b	91058	https://lionel.fr	pltv	Plateau en verre réchauffe plat	../../../content/productsImages/18C0776C-D2A4-4E57-B3DE-AE1E13FABB42-bis.jpg	12 cm x 24 cm	euros	f	17	ellyptique	1	2
2	btts	f	40746	238	Bottes en cuir, noirs, demi longueur	f	Manager	f	de	Intelligent	pixel methodical quantifying	78950	http://vivien.net	btts	Bottes cuir	../../../content/productsImages/b091f7d115bf473cb7934c78d5053e7a.webp	toutes pointures	euros	f	238	demi longueur	2	2
3	bllndf	f	51756	324.5	Ballon de foot pro, noir et blanc	f	Dinar	t	transmit Incredible IB	Algérie Rustic	Taka	90493	http://josse.net	bllndf	Ballon de foot	../../../content/productsImages/1268707_big.webp	usuel	euros	f	324.5	Noir & blanc	1	1
4	jtsk	t	94512	12730	Jet-Ski MT 245 cv vert et bleu	t	Soft	t	pink Sausages	Computers Jamaïque	Electronics	20046	http://thierry.fr	jtsk	Jet-Ski	../../../content/productsImages/22MY_ULTRA_310X_BK1_STU__2_.png	usual	euros	f	12730	vert et bleu	5	1
5	lnt	f	91355	325	Lunette plexi glass grands verres, légères	t	mint	t	exuding copying	services a	transmitting Account	18057	https://colin.info	lnt	Lunette plexi glass	../../../content/productsImages/lunettes-ekoi-persoevo8-noir-mirror-cat3.webp	wireless Practical a	euros	t	325		1	2
6	qrl	f	20850	119154	Aquarelle: la femme au buste dénudé de Jean Monnet	t	Producteur	t	teal Vision-oriented	Ergonomic Bedfordshire	ADP payment maximize	16931	https://brunehaut.eu	qrl	Aquarelle buste dénudé	../../../content/productsImages/383842_poster_m.jpg	global	euros	t	119154	impressionnisme	3	1
7	gtr	f	84403	250	guitare classique, façade jaune, flanc verni	t	mobile	f	Incredible	navigate	invoice a sticky	49383	https://landry.info	gtr	guitare classique	../../../content/productsImages/81tQhEEtiEL._AC_SX679_.jpg	usuel	euros	t	250	classique	2	2
8	scdlx	t	86844	735	Sac de luxe, marque Louis Vuitton (vrai) contrôlé sans contre façon	f	Clothing	t	Uganda primary Ergonomic	Games copying	Costa c du	85943	https://roland.fr	scdlx	Sac de luxe	../../../content/productsImages/louis-vuitton-cabas-onthego-gm-toile-monogram-sacs-à-main--M45320_PM2_Front view.webp	moyen	euros	f	735	Marron	3	1
9	mnjp	f	54388	65	Mini jupe sexy, couleur gris clair métallisé, taille dentellé	t	Macédoine transmit	t	Account Adolphe	Unbranded	adapter Auto neural	54441	https://amarande.fr	mnjp	Mini jupe	../../../content/productsImages/Mini-jupe-d-coli-re-pliss-e-en-dentelle-douce-pour-femmes-cosplay-court-mini-robe.jpg	toutes tailles	euros	t	65	ultra court	2	1
10	rbdm	t	30207	955	Robe de mariage style baroque, couleur rose satiné	f	Berkshire IB Card	f	Champagne-Ardenne Saint-Honoré	c Cross-platform partnerships	bi-directional Limousin	85148	https://acace.eu	rbdm	Robe de mariage	../../../content/productsImages/i.imgur.com_AKVFra1.jpeg	M, XL	euros	f	955	baroque	5	2
11	tdsgz	f	98311	985	Tondeuse à gazon Moteur 5 cv / 5 vitesses	f	Computer c deposit	f	withdrawal THX b	Metal	infomediaries partnerships b	91058	https://lionel.fr	tdsgz	Tondeuse à gazon	../../../content/productsImages/51269-tondeuse-18v-ryobi-2.jpg	Computer CFP orange	euros	f	985	vert	3	2
12	vllmdrn	t	40746	2575000	Villa moderne, avec piscine et sous-sol garage/grenier, 12 pièces totales garage compris	f	Manager	f	de	Intelligent	pixel methodical quantifying	78950	http://vivien.net	vllmdrn	Villa moderne	../../../content/productsImages/0ekucocxabry71r1voy73fz78hl3kvx9n434voz9c.jpg	AGP lavender	euros	t	2575000	Moderne	90	1
13	nkhpdtf	f	51756	25.1000000000000014	Ankh pendentif format: 3.14 cm x 6.28 cm, en argent inoxydable	f	Dinar	t	transmit Incredible IB	Algérie Rustic	Taka	90493	http://josse.net	nkhpdtf	Ankh pendentif	../../../content/productsImages/61biD6whgEL._AC_UY625_.jpg	3.14 cm x 6.28 cm	euros	f	25.1000000000000014	Antique	1	3
14	cptrpcl	t	94512	57	Pork pie, Chapeau tropical, en paille rigide	t	Soft	t	pink Sausages	Computers Jamaïque	Electronics	20046	http://thierry.fr	cptrpcl	Chapeau tropical	../../../content/productsImages/Lambert-porkpie-hat-khaki.webp	toutes tailles	euros	f	57	tropical	2	2
15	sctrymh	f	91355	625	Scooter Yamaha, 50 cm3, 75 00 km, couleur gris et bleu clair métallisé	t	mint	t	exuding copying	services a	transmitting Account	18057	https://colin.info	sctrymh	Scooter 50 cm3	../../../content/productsImages/productsImagesi.imgur.com_LFe1TYX.jpeg	50 cm3	euros	t	625	50 cm3	5	1
\.


--
-- Data for Name: rel_customer__preferred_locales; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.rel_customer__preferred_locales (preferred_locales_id, customer_id) FROM stdin;
\.


--
-- Name: sequence_generator; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.sequence_generator', 1050, true);


--
-- Name: application_user application_user_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.application_user
    ADD CONSTRAINT application_user_pkey PRIMARY KEY (id);


--
-- Name: commande commande_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.commande
    ADD CONSTRAINT commande_pkey PRIMARY KEY (id);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- Name: invoice invoice_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT invoice_pkey PRIMARY KEY (id);


--
-- Name: invoice_settings invoice_settings_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.invoice_settings
    ADD CONSTRAINT invoice_settings_pkey PRIMARY KEY (id);


--
-- Name: jhi_authority jhi_authority_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.jhi_authority
    ADD CONSTRAINT jhi_authority_pkey PRIMARY KEY (name);


--
-- Name: jhi_user_authority jhi_user_authority_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT jhi_user_authority_pkey PRIMARY KEY (user_id, authority_name);


--
-- Name: jhi_user jhi_user_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT jhi_user_pkey PRIMARY KEY (id);


--
-- Name: line_item line_item_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT line_item_pkey PRIMARY KEY (id);


--
-- Name: metadata metadata_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.metadata
    ADD CONSTRAINT metadata_pkey PRIMARY KEY (id);


--
-- Name: photo photo_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.photo
    ADD CONSTRAINT photo_pkey PRIMARY KEY (id);


--
-- Name: post post_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (id);


--
-- Name: preferred_locales preferred_locales_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.preferred_locales
    ADD CONSTRAINT preferred_locales_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: rel_customer__preferred_locales rel_customer__preferred_locales_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.rel_customer__preferred_locales
    ADD CONSTRAINT rel_customer__preferred_locales_pkey PRIMARY KEY (customer_id, preferred_locales_id);


--
-- Name: application_user ux_application_user__internal_user_id; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.application_user
    ADD CONSTRAINT ux_application_user__internal_user_id UNIQUE (internal_user_id);


--
-- Name: customer ux_customer__invoice_settings_id; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT ux_customer__invoice_settings_id UNIQUE (invoice_settings_id);


--
-- Name: customer ux_customer__metadata_id; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT ux_customer__metadata_id UNIQUE (metadata_id);


--
-- Name: invoice ux_invoice__commande_id; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT ux_invoice__commande_id UNIQUE (commande_id);


--
-- Name: line_item ux_line_item__product_id; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT ux_line_item__product_id UNIQUE (product_id);


--
-- Name: jhi_user ux_user_email; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_email UNIQUE (email);


--
-- Name: jhi_user ux_user_login; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_login UNIQUE (login);


--
-- Name: application_user fk_application_user__internal_user_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.application_user
    ADD CONSTRAINT fk_application_user__internal_user_id FOREIGN KEY (internal_user_id) REFERENCES public.jhi_user(id);


--
-- Name: jhi_user_authority fk_authority_name; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES public.jhi_authority(name);


--
-- Name: customer fk_customer__invoice_settings_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT fk_customer__invoice_settings_id FOREIGN KEY (invoice_settings_id) REFERENCES public.invoice_settings(id);


--
-- Name: customer fk_customer__metadata_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT fk_customer__metadata_id FOREIGN KEY (metadata_id) REFERENCES public.metadata(id);


--
-- Name: invoice fk_invoice__commande_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT fk_invoice__commande_id FOREIGN KEY (commande_id) REFERENCES public.commande(id);


--
-- Name: invoice fk_invoice__customer_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT fk_invoice__customer_id FOREIGN KEY (customer_id) REFERENCES public.customer(id);


--
-- Name: line_item fk_line_item__commande_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT fk_line_item__commande_id FOREIGN KEY (commande_id) REFERENCES public.commande(id);


--
-- Name: line_item fk_line_item__product_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT fk_line_item__product_id FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: photo fk_photo__post_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.photo
    ADD CONSTRAINT fk_photo__post_id FOREIGN KEY (post_id) REFERENCES public.post(id);


--
-- Name: photo fk_photo__product_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.photo
    ADD CONSTRAINT fk_photo__product_id FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: post fk_post__user_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT fk_post__user_id FOREIGN KEY (user_id) REFERENCES public.application_user(id);


--
-- Name: product fk_product__user_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_product__user_id FOREIGN KEY (user_id) REFERENCES public.application_user(id);


--
-- Name: rel_customer__preferred_locales fk_rel_customer__preferred_locales__customer_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.rel_customer__preferred_locales
    ADD CONSTRAINT fk_rel_customer__preferred_locales__customer_id FOREIGN KEY (customer_id) REFERENCES public.customer(id);


--
-- Name: rel_customer__preferred_locales fk_rel_customer__preferred_locales__preferred_locales_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.rel_customer__preferred_locales
    ADD CONSTRAINT fk_rel_customer__preferred_locales__preferred_locales_id FOREIGN KEY (preferred_locales_id) REFERENCES public.preferred_locales(id);


--
-- Name: jhi_user_authority fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.jhi_user(id);


--
-- PostgreSQL database dump complete
--

