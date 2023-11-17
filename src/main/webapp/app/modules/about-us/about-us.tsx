// AboutUs.tsx

import React from 'react';
import './about-us.scss';

interface TeamMember {
  name: string;
  photoUrl: string;
}

const AboutUs: React.FC = () => {
  return (
    <div className="about-us-container">
      <h1>MADASKILL</h1>
      <p>Fondée en 2022</p>
      <p>Association créée de fait, oeuvrant pour la Révolution Technologique et Industrielle "Verte"</p>
      <br />
      <br />
      <div className="list-container">
        <div className="menbers">
          <div className="bloc-element">
            <h2>ÉQUIPE</h2>
            <br />
            <br />
            <ul className="team-member">
              <img src="../../../content/superHeros/Iron man.jpg/" alt="Serge Rafanomezantsoa" className="member-photo" />
              <div className="member-info">
                <p>Serge Rafanomezantsoa</p>
              </div>
            </ul>
            <ul className="team-member">
              <img src="url_d../../../content/superHeros/Spiderman.jpg" alt="Tiana Randria" className="member-photo" />
              <div className="member-info">
                <p>Tiana Randria</p>
              </div>
            </ul>
            <ul className="team-member">
              <img src="../../../content/superHeros/Cat woman.jpg" alt="Alice Bezara" className="member-photo" />
              <div className="member-info">
                <p>Alice Bezara</p>
              </div>
            </ul>
            <ul className="team-member">
              <img src="../../../content/superHeros/Superman.jpg" alt="Nirina Rakotondramanana" className="member-photo" />
              <div className="member-info">
                <p>Nirina Rakotondramanana</p>
              </div>
            </ul>
            <ul className="team-member">
              <img src="../../../content/superHeros/Daredevil.jpg" alt="Christian Ralay" className="member-photo" />
              <div className="member-info">
                <p>Christian Ralay</p>
              </div>
            </ul>
            <ul className="team-member">
              <img src="../../../content/superHeros/Batman.jpg" alt="Tsiry Soloniaina" className="member-photo" />
              <div className="member-info">
                <p>Tsiry Soloniaina</p>
              </div>
            </ul>
          </div>
        </div>
        <div className="bloc-element">
          <h2>CONTEXTE</h2>
          <p>
            <br />
            <br />
            <h3>Etat des lieux</h3>
            En hélicoptère ou en jet privé, dans les campagnes et les grandes villes, Andry Rajoelina a, jusqu'au bout, sillonné le pays et
            battu le rappel des troupes pour s'assurer de sa réélection à la tête de Madagascar. Les Malgaches sont appelés aux urnes ce
            jeudi 16 novembre pour choisir leur prochain président. Une partie de l'opposition a appelé au boycott du scrutin et dénoncé des
            irrégularités alors que le président sortant, Andry Rajoelina, se dit assuré d'une victoire au premier tour.
            <br />
            <br /> Pourtant, cinq ans après son accession au pouvoir, à l'issue d'un bras de fer de plusieurs mois avec son prédécesseur, le
            bilan du président Rajoelina ne soulève plus beaucoup d'enthousiasme. En effet, s'il dit compter sur un fort soutien populaire
            et Dieu pour remporter un second mandat, sur le plan économique, Andry Rajoelina n'a pas fait de miracle, malgré l'annonce en
            2018 et en grande pompe du « Plan Émergence Madagascar » (PEM).
            <br />
            <br />
            Entre-temps, la Grande Île a subi de plein fouet les conséquences de la pandémie de Covid-19 qui a mis à l'arrêt le secteur
            essentiel du tourisme, avec une récession de 4,2 % du PIB selon la Banque mondiale. Les conséquences ont été dramatiques dans ce
            pays où les filets de sécurité sociale sont très minces. Et les choses ne font qu'empirer depuis le déclenchement de la guerre
            russe en Ukraine, en effet, la Grande Île reste prise dans une spirale inflationniste et peine à renouer avec la croissance, sa
            monnaie, l'ariary, connaît une forte dépréciation.
          </p>
          <p>
            <h3>Les racines de la pauvreté</h3>
            Mais la situation de ce pays est bien plus paradoxale : bien dotée en ressources naturelles, Madagascar a connu le plus fort
            appauvrissement au monde depuis 1960, date de l'indépendance. Les chiffres sont parlants : entre 1960 et 2020, le revenu par
            habitant de Madagascar a diminué de 45 %, rappelle sans détour dans une tribune publiée en mai 2023, sous le titre « Comment
            Madagascar peut-il rompre le cercle vicieux de la pauvreté ? », les représentantes de la Banque mondiale et de l'International
            Finance Corporation, Marie-Chantal Uwanyiligira et Marcelle Ayo, ainsi que l'économiste Francis Mulangu. Ce qui fait de l'île
            rouge un cas très particulier, unique, puisque le pays n'est pas en guerre ni dans un contexte de violence, qui expliquerait ce
            tragique recul. D'après les experts, les maigres progrès enregistrés lors des périodes de relative stabilité sont presque
            systématiquement balayés par les crises successives. Ils font également le lien avec le manque de transparence au c?ur du
            pouvoir, et la capture de l'État par des élites.
            <br />
            <br />
            Selon la Banque mondiale, la part de la population vivant en dessous du seuil national de pauvreté est passée de 72,5 % en 2012
            à 75,2 % en 2022, les nombreux bidonvilles de la capitale en témoignent. Plus de la moitié de l'économie de l'île serait
            informelle, échappant toujours à tout contrôle. Le secteur privé est d'après les spécialistes de la Banque mondiale trop petit
            et peu compétitif, caractérisé par de faibles niveaux d'investissement, ce qui empêche « de créer des emplois, de stimuler la
            croissance économique, et donc de réduire la pauvreté ». Plus de 90 % de la population en âge de travailler reste engagée dans
            l'agriculture de subsistance et les services informels. Sans compter le poids de la corruption, qui gangrène toujours le pays.
            La faiblesse des institutions et le contrôle des élites empêchent de lutter avec efficacité contre la corruption.
            <br />
            <br />
            Des défis qui impliquent de nombreuses autres données, comme l'indice de capital humain de l'île, qui est de l'ordre de 0,39
            (l'un des plus faibles au monde). En fait, faute d'éducation et de bonne santé, les enfants malgaches ont peu de chance à
            l'heure actuelle de devenir des adultes productifs. Quant à la principale richesse de l'île, la vanille, qui représente 5 % du
            PIB malgache, cette industrie est au centre d'une lutte de pouvoir entre le gouvernement et les grands acteurs du secteur, pour
            la plupart étrangers. Les autorités d'Antananarivo auraient aggravé les effets de la surproduction avec la récente réforme du
            secteur sur les prix et les exportations. L'île rouge subit aussi des revers climatiques. Des tempêtes tropicales et des
            cyclones s'y sont abattus ces dernières années. En 2021, le sud de Madagascar a été frappé par une sécheresse provoquant une
            terrible famine, malgré plusieurs alertes de l'ONU.
            <br />
            <br />
            <h3>Les attentes de la population sont toujours là</h3>
            Concrètement, près de la moitié de la population n'a pas accès à l'eau et seulement 12 % à l'assainissement, selon la Banque
            mondiale. Avec des infrastructures vieillissantes, la compagnie publique Jirama est incapable de répondre aux besoins des 3,4
            millions qui vivent entassés à Antananarivo et ses faubourgs. En cas de panne, des camions-citernes peuvent alimenter les
            maisons confortablement assises sur les hauteurs de la ville. Mais dans les coins populaires appelés ici les « bas-quartiers »,
            au pied des collines, ils n'apparaissent que rarement. Et certains foyers ne sont tout simplement pas raccordés. Quels que
            soient les résultats de l'élection présidentielle, le prochain président portera la lourde responsabilité de redresser d'urgence
            l'économie et surtout de faire rimer croissance durable et développement.
          </p>
        </div>
      </div>
    </div>
  );
};

export default AboutUs;
