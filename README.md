<h1 align="center">Veteriner Yönetim Sistemi</h1>
<h3 align="center">Bu proje, bir veteriner kliniğinin kendi işlerini yönetebildiği bir API geliştirmeyi amaçlamaktadır. Bu uygulama, veteriner çalışanları tarafından kullanılmak üzere tasarlanmıştır ve aşağıdaki özellikleri içermektedir:

</h3>  

<br/>

<p align="center"> 
  <img src="Pictures/Veterinary-Management-System-UML.png" alt="Sistem Ana Görseli" width="100%" height="50%">
</p>

<!-- TABLE OF CONTENTS -->
<h2 id="table-of-contents"> :book: İçerik</h2>

<details open="open">
  <summary>İçerik</summary>
  <ol>
    <li><a href="#proje-ozeti"> ➤ Proje Özeti</a></li>
    <li><a href="#teknolojiler"> ➤ Kullanılan Teknolojiler</a></li>
    <li><a href="#kurulum"> ➤ Kurulum ve Başlatma</a></li>
    <li><a href="#kullanici-yonetimi"> ➤ Kullanıcı Yönetimi</a></li>
    <li><a href="#otel-yonetimi"> ➤ Otel Yönetimi</a></li>
    <li><a href="#oda-yonetimi"> ➤ Oda Yönetimi</a></li>
    <li><a href="#rezervasyon-islemleri"> ➤ Rezervasyon İşlemleri</a></li>
    <li><a href="#sistem-gorselleri"> ➤ Program İçi Görseller</a></li>
    <li><a href="#iletisim"> ➤ İletişim</a></li>
  </ol>
</details>


![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<!-- VİDEO -->
<h2 id="proje-ozeti-video-kaydı"> :video_camera: Proje Özeti Video Kaydı</h2>

<p>
✤ <a href="https://www.youtube.com/channel/UCEJ2dE8VjikdFug6d26wDBA">Video Linki</a> <br>
</p>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- PROJECT OVERVIEW -->
<h2 id="proje-ozeti"> :mag_right: Proje Özellikleri</h2>

<p align="justify"> 
Özellikler:
</p>

- Veteriner Doktorları Kaydetme
- Doktorların Çalışma Günlerini Kaydetme
- Müşterileri Kaydetme
- Müşterilere Ait Hayvanları Kaydetme
- Hayvanlara Uygulanan Aşıları Kaydetme
- Hayvanlar İçin Veteriner Hekimlere Randevu Oluşturma
- Randevuların Yönetimi

## Entity Sınıfları

### Animal
- id: Long
- name: String
- species: String
- breed: String
- gender: String
- colour: String
- dateOfBirth: LocalDate

### Customer
- id: Long
- name: String
- phone: String
- mail: String
- address: String
- city: String

### Vaccine
- id: Long
- name: String
- code: String
- protectionStartDate: LocalDate
- protectionFinishDate: LocalDate

### Doctor
- id: Long
- name: String
- phone: String
- mail: String
- address: String
- city: String

### AvailableDate
- id: Long
- availableDate: LocalDate

### Appointment
- id: Long
- appointmentDate: LocalDateTime

## API Temel Özellikleri

### Hayvanların ve Sahiplerinin Yönetimi
- Hayvanları kaydetme, bilgilerini güncelleme, görüntüleme ve silme
- Hayvan sahiplerini kaydetme, bilgilerini güncelleme, görüntüleme ve silme
- Hayvan sahiplerini isme göre filtreleme
- Hayvanları isme göre filtreleme
- Hayvan sahibine göre hayvanları listeleme

### Uygulanan Aşıların Yönetimi
- Hayvanlara uygulanan aşıları kaydetme, bilgilerini güncelleme, görüntüleme ve silme
- Aynı tip aşının koruyuculuk süresi dolmamışsa yeni aşı kaydedememe
- Hayvan id’sine göre aşı kayıtlarını listeleme
- Aşı koruyuculuk bitiş tarihi belirli aralıkta olan hayvanları listeleme

### Randevu Yönetimi
- Randevuları oluşturma, güncelleme, görüntüleme ve silme
- Randevuları tarih ve saat içerecek şekilde kaydetme
- Doktorların müsait günlerinde ve saatlerinde randevu oluşturma
- Randevuları tarih aralığına ve doktora göre filtreleme
- Randevuları tarih aralığına ve hayvana göre filtreleme

### Veteriner Doktor Yönetimi
- Veteriner doktorları kaydetme, bilgilerini güncelleme, görüntüleme ve silme

### Doktorların Müsait Günlerinin Yönetimi
- Doktorların müsait günlerini ekleme, bilgilerini güncelleme, görüntüleme ve silme

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- USED TECHNOLOGIES -->
<h2 id="teknolojiler"> :computer: Kullanılan Teknolojiler</h2>

<p align="justify"> 
Projede kullanılan teknolojiler:

[![made-with-java](https://img.shields.io/badge/Made%20with-Java-green.svg)](https://www.java.com/tr/) <br>
[![made-with-Swing](https://img.shields.io/badge/Made%20with-SpringBoot-red.svg)](https://start.spring.io/) <br>
[![made-with-SQL](https://img.shields.io/badge/Made%20with-Postman-orange.svg)](https://www.postman.com/) <br>
[![made-with-SQL](https://img.shields.io/badge/Made%20with-PostgreSQL-blue.svg)](https://www.mysql.com/) <br>
[![made-with-ide](https://img.shields.io/badge/IntelliJ%20IDEA%20%2F%20Eclipse%20%2F%20Herhangi%20bir%20Java%20-IDE-blue.svg)](https://www.python.org/) <br>

</p>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- SETUP AND LAUNCH -->
<h2 id="kurulum"> :hammer: Kurulum ve Başlatma</h2>

<p align="justify"> 
Projeyi  klonlamak ve çalıştırmak için aşağıdaki adımları takip edin:

```bash
git clone https://github.com/behcetmuhammed/tourism-agency-system-gp.git
cd tourism-agency-system-gp
```
Projeyi IDE'nizde açın ve `App` sınıfını çalıştırın.
</p>

<h3 id="kurulum"> :warning: Uyarı: Bu proje, `JDK 18 Amazon Corretto 18.0.2` ile uyumludur. Lütfen bu JDK sürümünü kullanarak projeyi çalıştırın.</h3>


![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- USER MANAGEMENT -->
<h2 id="kullanici-yonetimi"> :busts_in_silhouette: Kullanıcı Yönetimi</h2>

<p align="justify">
Sistemde iki ana kullanıcı türü bulunur: Yönetici ve Acenta Çalışanı. Yönetici kullanıcıları, sistem üzerinde geniş yetkilere sahiptirler ve acenta çalışanlarını listeleme, ekleme, silme, güncelleme gibi işlemleri gerçekleştirebilirler. Ayrıca, kullanıcıları rollerine göre filtreleyebilirler. Acenta çalışanları ise otel ve oda yönetimi, dönem yönetimi ve fiyat yönetimi gibi işlevlere erişerek müşteri rezervasyonları üzerinde işlem yapabilirler.
</p>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- HOTEL MANAGEMENT -->
<h2 id="otel-yonetimi"> :hotel: Otel Yönetimi</h2>

<p align="justify">
Patika Turizm Acentesi'nin anlaşmalı olduğu otellerin yönetimi için tasarlanmış olan Otel Yönetimi modülü, acenta çalışanlarının otel listeleme ve yeni otel ekleme işlemlerini gerçekleştirmesini sağlar. Her otel kaydında otelin adı, adresi, e-posta adresi, telefon numarası, yıldız sayısı ve otelin sunduğu tesis özellikleri gibi bilgiler bulunur. Ayrıca, otellere ait pansiyon tipleri ve dönem bilgileri de bu modül üzerinden yönetilerek fiyatlandırma ve rezervasyon süreçleri için gerekli altyapı oluşturulur.
</p>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- ROOM MANAGEMENT -->
<h2 id="oda-yonetimi"> :door: Oda Yönetimi</h2>

<p align="justify">
Oda Yönetimi modülü, acenta çalışanlarının otellerdeki odaları yönetmelerini sağlar. Bu modül sayesinde odaların listelenmesi, yeni odaların eklenmesi ve odaların otel adı, şehir veya belirli bir tarihe göre aranması mümkündür. Oda kaydı sırasında, odanın tipi (tek kişilik, çift kişilik, suit vb.), oda özellikleri (yatak sayısı, televizyon, minibar, oyun konsolu vb.), oda fiyatı ve oda stoğu gibi bilgiler girilir.

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- RESERVATION OPERATIONS -->
<h2 id="rezervasyon-islemleri"> :clipboard: Rezervasyon İşlemleri</h2>

<p align="justify">
Rezervasyon İşlemleri modülü, acenta çalışanlarının müşteri hizmetlerini hızlı ve etkili bir şekilde sunmalarını sağlar. Rezervasyon yapılırken müşteri iletişim bilgileri ve misafir bilgileri sisteme girilir. Bu bilgilere göre toplam fiyat otomatik olarak hesaplanır. Acenta çalışanları rezervasyonları listeleyebilir, güncelleyebilir ve silebilirler. Rezervasyon tamamlandığında, ilgili odanın stoğu otomatik olarak bir azalır.
</p>


![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<!-- PROGRAM SCREENSHOTS -->
<h2 id="sistem-gorselleri"> :camera: Program İçi Görseller</h2>

<p align="justify"> 

### Login Ekranı

<p>
Kullanıcı adı ve şifre girildikten sonra, sistem tarafından aşağıdaki şekilde yönlendirilirsiniz:
  
- Eğer admin kullanıcı adı ve şifresi girildiyse, **Admin Paneli** ne giriş yapılır.
- Eğer çalışan kullanıcı adı ve şifresi girildiyse, **Çalışan Paneli**ne giriş yapılır.
</p>

1)
![](pictures/Login.png)

### Admin Paneli 
<p>Admin ve Çalışan Listesi:</p>

1)
![](pictures/User.png)

<p>
  Yeni kullanıcı eklemek, mevcut kullanıcıyı güncellemek veya silmek için:
  
- İşlem yapmak istediğiniz kullanıcıyı seçmek için kullanıcı satırına sağ veya sol tıklayın.
- Açılan pop-up menüden ilgili işlemi (Ekle, Güncelle veya Sil) seçin.
</p>

2)
![](pictures/PopUpUser.png)

<p>
Yeni kullanıcı ekleme paneli
</p>

3)
![](pictures/AddUser.png)

### Otel Yönetimi
1)
![](pictures/Employee.png)

2)
![](pictures/AddPension.png)

3)
![](pictures/AddHotel.png)

### Pansiyon Yönetimi
1)
![](pictures/PensionManagement.png)

### Sezon Yönetimi
1)
![](pictures/SeasonManagement.png)

### Oda Yönetimi
1)
![](pictures/Room.png)

### Rezervasyon İşlemleri
1)
![](pictures/Reservation.png)

### Validasyonlar
1)
![](pictures/Filter.png)

2)
![](pictures/Fil1.png)

</p>


![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<!-- CONTACT -->
<h2 id="iletisim"> :phone: İletişim ve Bilgiler</h2>

<p>
✤ <a href="https://www.linkedin.com/in/emir-muhammed/">LinkedIn</a> <br>
✤ <a href="https://github.com/behcetmuhammed">GitHub</a> <br>
</p>
