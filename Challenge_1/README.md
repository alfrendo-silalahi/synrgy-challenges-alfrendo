# Challenge Chapter 1 - BinarFud Application

Aplikasi BinarFud adalah sebuah sistem yang bisa menampilkan detail pesanan yang mencakup harga, jumlah pesanan, variasi pesanan, dan tentunya melakukan pemesanan. Selain itu, ketika user selesai melakukan pembayaran, akan disediakan struk pembayaran pada file struk.txt yang mana akan diperbaharui setiap terdapat pesanan yang baru.   

## Installation

- Silahkan lakukan clone pada repository ini.

- Lakukan install pada dependency yang dibutuhkan menggunakan perintah berikut. (Biasanya akan secara otomatis diinstall oleh IDE ketika project pertama kali dibuka)
```
mvn install
```

## Run

Untuk menjalankan aplikasi BinarFund terdapat dua cara, yaitu:

- Menggunakan tools bawaan IDE yang tersedia, dan melakukan ekseksusi pada file Main.java. (Pada IntelliJ menggunakan button segitiga berwarna hijau)

- Menggunakan perintah maven. Perintah maven berikut bukan default command dari maven, namun merupakan salah satu plugin eksternal yang ditambahkan ke aplikasi.
```
mvn exec:java
```