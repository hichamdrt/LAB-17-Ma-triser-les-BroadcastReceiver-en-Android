# 📡 LAB 17 – Maîtriser les BroadcastReceiver en Android

---

## 📖 Présentation

Ce laboratoire a pour objectif de comprendre et maîtriser les **BroadcastReceiver** en Android.

Nous allons implémenter :

- ✅ Un **Receiver Dynamique** (Airplane Mode)
- ✅ Un **Receiver Statique** (BOOT_COMPLETED)
- ✅ Un **Custom Broadcast**
- ✅ La gestion des permissions et des règles Android 12+
  <img width="432" height="815" alt="image" src="https://github.com/user-attachments/assets/67b990de-a53f-4c9a-9e4e-2aa2f02ebce6" />
<img width="426" height="807" alt="image" src="https://github.com/user-attachments/assets/8dd6d9e3-5b41-4e2d-8ddb-c9ff399b248c" />
<img width="424" height="769" alt="image" src="https://github.com/user-attachments/assets/148c41fe-c53d-44ba-b57f-5cbfeb709900" />


Ce lab permet de comprendre le système de communication interne d’Android basé sur les **Intents Broadcast**.

---

# 🎯 Objectifs pédagogiques

À la fin de ce lab, vous serez capable de :

- Comprendre ce qu’est un Broadcast
- Créer un BroadcastReceiver dynamique
- Créer un BroadcastReceiver statique
- Déclarer un Receiver dans le Manifest
- Gérer la permission `RECEIVE_BOOT_COMPLETED`
- Envoyer et recevoir un Custom Broadcast

---

# 🧠 Théorie – Qu’est-ce qu’un Broadcast ?

Un **Broadcast** est un message envoyé par le système Android ou par une application.

Exemples :
- 📶 Mode avion activé
- 🔋 Batterie faible
- 📱 Téléphone redémarré
- 📡 WiFi activé
- 📦 Broadcast personnalisé

Un BroadcastReceiver écoute ces messages et exécute du code.

---

# 🏗 Architecture du projet

```
ReceiverDemo
│
├── MainActivity.java
├── AirplaneModeReceiver.java
├── BootReceiver.java
├── CustomEventReceiver.java
│
├── res/layout/activity_main.xml
└── AndroidManifest.xml
```

---

# ✅ 1️⃣ Receiver Dynamique – Airplane Mode

### 📌 Classe : `AirplaneModeReceiver.java`

Écoute :

```java
Intent.ACTION_AIRPLANE_MODE_CHANGED
```

Ce receiver est enregistré dynamiquement dans `MainActivity` via :

```java
registerReceiver(airplaneReceiver, filter);
```

### ✅ Fonctionnement :

- Active le receiver via bouton
- Affiche un Toast si le mode avion change
- Peut être désactivé dynamiquement

---

# ✅ 2️⃣ Receiver Statique – BOOT_COMPLETED

### 📌 Classe : `BootReceiver.java`

Écoute :

```xml
android.intent.action.BOOT_COMPLETED
```

Déclaré dans le Manifest :

```xml
<receiver
    android:name=".BootReceiver"
    android:exported="false">
    <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED"/>
    </intent-filter>
</receiver>
```

### ✅ Permission nécessaire :

```xml
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>
```

### ✅ Fonctionnement :

- Déclenché au redémarrage du téléphone
- Affiche un Toast indiquant que le téléphone a démarré

---

# ✅ 3️⃣ Custom Broadcast

### 📌 Envoi depuis MainActivity :

```java
Intent intent = new Intent("com.example.receiverdemo.CUSTOM_EVENT");
intent.putExtra("message", "Bonjour depuis le custom broadcast !");
sendBroadcast(intent);
```

### 📌 Receiver associé :

```java
CustomEventReceiver extends BroadcastReceiver
```

Affiche :

```
Custom reçu : Bonjour depuis le custom broadcast !
```

---

# 🔐 Android 12+ – Sécurité

Depuis Android 12 :

✅ `android:exported` est obligatoire

```xml
android:exported="false"
```

Cela empêche d’autres applications d’envoyer des broadcasts à ton receiver.

---

# 🧪 Étapes de test

## ✅ Test 1 – Receiver Dynamique

1. Lancer l’application
2. Cliquer sur "Activer Receiver Avion"
3. Activer/Désactiver le mode avion

Résultat attendu :

✔ Toast affiché

---

## ✅ Test 2 – Custom Broadcast

1. Cliquer sur "Envoyer Custom Broadcast"

Résultat attendu :

✔ Toast "Custom reçu"

---

## ✅ Test 3 – BootReceiver

1. Redémarrer le téléphone réel
2. Attendre quelques secondes

Résultat attendu :

✔ Toast affiché après démarrage

⚠️ Sur émulateur, BOOT_COMPLETED ne fonctionne pas toujours correctement.

---

# ⚠ Bonnes pratiques

- Toujours désenregistrer un receiver dynamique dans `onDestroy()`
- Utiliser `exported="false"` par défaut
- Éviter les receivers statiques inutiles (Android limite leur usage)
- Ne pas bloquer le thread principal dans `onReceive()`

---

# 📊 Différence entre Dynamique et Statique

| Dynamique | Statique |
|------------|------------|
| Enregistré en code | Déclaré dans le Manifest |
| Fonctionne seulement quand app ouverte | Fonctionne même si app fermée |
| Plus sécurisé | Plus limité Android moderne |

---

# 🔒 Sécurité

- Les broadcasts système peuvent être sensibles
- Depuis Android 8+, restrictions renforcées
- Certaines actions ne sont plus autorisées en statique

---

# 🎯 Résultat final

✔ Receiver dynamique fonctionnel  
✔ Receiver statique BOOT fonctionnel  
✔ Custom broadcast fonctionnel  
✔ Respect des règles Android modernes  
✔ Projet pédagogique complet  

---

# 👨‍💻 Auteur

Projet réalisé dans le cadre du module :

**Programmation Mobile – Android avec Java**

Année universitaire 2025–2026

---

# ✅ Conclusion

Ce laboratoire permet de maîtriser un concept fondamental d’Android :

La communication via BroadcastReceiver.

Il permet de comprendre :

- Le fonctionnement interne d’Android
- Les restrictions modernes de sécurité
- Les différences entre enregistrement statique et dynamique

Ce mécanisme est utilisé dans :

- Applications système
- Applications réseau
- Gestion batterie
- Services d’arrière-plan
- Applications IoT
