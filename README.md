## 📱 Overview

This repository contains a completed **Android Mobile Application** built using **Jetpack Compose**
and modern Android development best practices.

The application showcases media consumption features commonly used in OTT platforms, including image
carousels, HLS video playback, subscription handling, and Google IMA ad integration using *
*ExoPlayer (Media3)**.

---

## 🛠 Tech Stack

- Kotlin
- Jetpack Compose
- ExoPlayer (Media3)
- Google IMA SDK
- SharedPreferences
- Material 3
- MVVM Architecture

---

## 🏠 Home Screen

### Subscription Status

- The Home Screen includes a **Switch** that allows users to mark themselves as **Subscribed** or *
  *Not Subscribed**.
- The subscription state is saved using **SharedPreferences**.
- The selected state persists across app restarts.

**Behavior:**

- **Subscribed users** → Play content without ads
- **Non-subscribed users** → Ads are enabled during playback

---

### Image Carousels

The Home Screen contains **two horizontally scrolling carousels**:

#### Vertical Image Carousel

- Displays **10 vertically oriented images**
- Scrolls horizontally
- Clicking any image opens the video player

#### Horizontal Image Carousel

- Displays **10 horizontally oriented images**
- Scrolls horizontally
- Clicking any image opens the video player

Carousels coexist on the same screen and are optimized for smooth scrolling and performance.

---

## 🎬 Video Player

### HLS Media Playback

- Media is played using **ExoPlayer (Media3)**
- Supports adaptive streaming via **HLS**

Sample HLS stream used:
https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8
---

### Ads Integration (Google IMA)

- Google IMA ads are integrated for **non-subscribed users**
- Supports:
-
    - Pre-roll ads
    - Mid-roll ads
    - Post-roll ads

Sample IMA Ad Tag:
https://pubads.g.doubleclick.net/gampad/ads?iu=/21775744923/external/vmap_ad_samples&sz=640x480&cust_params=sample_ar%3Dpremidpost&ciu_szs=300x250&gdfp_req=1&ad_rule=1&output=vmap&unviewed_position_start=1&env=vp&cmsid=496&vid=short_onecue&correlator=

#### Seamless Playback

- Ad playback transitions smoothly with content
- After mid-roll ads, the video **resumes from the exact playback position**

---

## 🎮 Custom Player Controls

- **custom video play back controls**
- Designed and implemented using **Jetpack Compose**
- Integrated directly with ExoPlayer
- Clean UI with smooth animations and responsive interactions

---

## 🎨 UI & Code Quality

- Modern UI using **Material 3**
- Consistent colors
- Clean, readable, and well-structured code
- Clear separation of concerns using MVVM
- Meaningful comments included for maintainability

---

## ✅ Summary

This project demonstrates:

- Modern Android UI using Jetpack Compose
- Media playback using ExoPlayer
- Google IMA ad integration
- Persistent subscription handling
- Custom video player controls
- Clean architecture and polished UI

---

**Built with creativity and attention to detail 🙂**
