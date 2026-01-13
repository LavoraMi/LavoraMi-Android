# <img src="app/src/main/ic_launcher-playstore.png" alt="Icon" width="30px" height="30px"> LavoraMi

<div align="center">

[![Android](https://img.shields.io/badge/Android-8.0%2B-green.svg?style=flat)](https://www.android.com/)
[![Kotlin](https://img.shields.io/badge/Java-11%2B-orange.svg?style=flat)](https://www.java.com/)
[![License](https://img.shields.io/badge/License-MIT-green.svg?style=flat)](LICENSE)
[![Repository](https://img.shields.io/badge/GitHub-LavoraMi--Android-black?style=flat&logo=github)](https://github.com/Andrea-Filice/LavoraMi-Android)

Un'app Android intuitiva per monitorare i lavori di manutenzione del trasporto pubblico.

 **[Segnala un Bug](https://github.com/Andrea-Filice/LavoraMi-Android/issues)** • **[Richiedi una Feature](https://github.com/Andrea-Filice/LavoraMi-Android/issues)**

</div>

---

## 📋 Indice

- [Panoramica](#-panoramica)
- [Caratteristiche](#-caratteristiche)
- [Requisiti](#-requisiti)
- [Installazione](#-installazione)
- [Architettura](#-architettura)
- [Contributi](#-contributi)
- [Licenza](#-licenza)

---

## 🎯 Panoramica

**LavoraMi** è un'applicazione Android progettata per fornire informazioni in tempo reale sui lavori di manutenzione che interessano le reti di trasporto pubblico. L'app consente agli utenti di:

- Visualizzare i lavori in corso e pianificati
- Tracciare il progresso dei lavori con timeline interattive
- Consultare le linee di trasporto interessate
- Ricevere notifiche sullo stato dei lavori

Perfetta per pendolari che desiderano rimanere sempre informati sulle interruzioni e le modifiche ai servizi di trasporto.

---

## ✨ Caratteristiche

### 📱 Interfaccia Utente
- **Bottom Navigation** - Navigazione intuitiva tra sezioni
  - 🏠 **Home** - Visualizzazione riepilogativa dei lavori
  - 🚇 **Linee** - Dettagli specifici per ogni linea di trasporto
  - ⚙️ **Impostazioni** - Localizzazione geografica dei lavori

### 🔔 Notifiche
- Avvisi in tempo reale sullo stato dei lavori
- Notifiche personalizzabili per linee specifiche
- Gestione centralizzata delle notifiche

### 🗺️ Integrazione Mappe
- Visualizzazione dei lavori su mappa interattiva
- Indicazione geografica delle stazioni interessate
- Integrazione con Google Maps

### 🎨 Design Moderno
- Interfaccia nativa Material Design
- Design responsivo e accessibility-focused
- Supporto per modalità scura

---

## 📋 Requisiti

- **Android** 8.0 (API 26) o superiore
- **Android Studio** 2022.1 o superiore
- **Java Development Kit (JDK)** 11 o superiore
- **Android SDK** con Build Tools 34.0.0

---

## 🚀 Installazione

### Clonare il Repository
```bash
git clone https://github.com/Andrea-Filice/LavoraMi-Android.git
cd LavoraMi-Android
```

### Aprire in Android Studio
```bash
# Su macOS
open -a "Android Studio" .

# O aprire manualmente Android Studio e selezionare la cartella
```

### Compilare e Eseguire
1. Attendere il sincronizzamento della configurazione Gradle
2. Selezionare il dispositivo target (emulatore o dispositivo reale)
3. Premere `Shift + F10` (o il pulsante Run) per compilare ed eseguire
4. L'app si avvierà automaticamente sul dispositivo

### Requisiti di Build
- Gradle 8.0 o superiore (incluso nel wrapper)
- Dipendenze gestite automaticamente dal file `build.gradle.kts`

---

## 🏗️ Architettura

### Architettura MVVM
L'app segue il pattern **Model-View-ViewModel**:

```
┌─────────────────────────────────────┐
│        Android Activities           │
│ (MainActivity, LinesActivity, etc)  │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│       ViewModel & Adapters          │
│   (Business Logic & State)          │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│          Data Layer                 │
│  (APIWorks, Local Database)         │
└─────────────────────────────────────┘
```

### Componenti Principali

| File | Descrizione |
|------|-------------|
| `MainActivity.java` | Activity principale con tab navigation |
| `LinesActivity.java` | Visualizzazione delle linee di trasporto |
| `SettingsActivity.java` | Impostazioni e localizzazione |
| `WorkAdapter.java` | Adapter per visualizzare i lavori in lista |
| `APIWorks.java` | Gestione delle API e dei dati remoti |
| `EventDescriptor.java` | Modello dei lavori di manutenzione |

---

## 🤝 Contributi

I contributi sono benvenuti! Per contribuire:

1. **Fork** il repository
2. **Crea un branch** per la tua feature (`git checkout -b feature/AmazingFeature`)
3. **Commit** le tue modifiche (`git commit -m 'Add AmazingFeature'`)
4. **Push** al branch (`git push origin feature/AmazingFeature`)
5. **Apri una Pull Request**

### Linee Guida per i Contributi
- Seguire lo stile di codice Java di Google
- Includere commenti per codice complesso
- Testare le modifiche prima di inviare la PR
- Aggiornare la documentazione se necessario

---

## 🐛 Segnalazione Bug

Hai trovato un bug? Per favore, [apri un issue](https://github.com/Andrea-Filice/LavoraMi-Android/issues) con:
- Descrizione del problema
- Passaggi per riprodurlo
- Comportamento atteso vs. reale
- Versione Android e dispositivo

---

## 💡 Richieste di Feature

Hai un'idea per migliorare LavoraMi? [Suggerisci una feature](https://github.com/Andrea-Filice/LavoraMi-Android/issues) descrivendo:
- L'idea e il beneficio per l'utente
- Possibili casi d'uso
- Eventuali alternative considerate

---

## 📄 Licenza

Questo progetto è licenziato sotto la Licenza MIT - vedi il file [LICENSE](LICENSE) per i dettagli.

```
MIT License

Copyright (c) 2026 Andrea Filice

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or copies
of the Software, and to permit persons to whom the Software is furnished to
do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

---

## 👨‍💻 Autori

**Andrea Filice**
- 🔗 [GitHub](https://github.com/Andrea-Filice)
- 📧 Contattami attraverso il repository

**Tommaso Ruggeri**
- 🔗 [GitHub](https://github.com/TizioBanana08)
- 📧 Contattami attraverso il repository

---

<div align="center">

**Fatto con ❤️ per chi ama la mobilità urbana milanese**

[![GitHub Stars](https://img.shields.io/github/stars/Andrea-Filice/LavoraMi-Android?style=social)](https://github.com/Andrea-Filice/LavoraMi-Android)
[![GitHub Followers](https://img.shields.io/github/followers/Andrea-Filice?style=social)](https://github.com/Andrea-Filice)

</div>
