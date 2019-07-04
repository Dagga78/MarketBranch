// Initialize Cloud Firestore through Firebase
firebase.initializeApp({
	apiKey : "AIzaSyCHd1t0Smr4d6XPUVmLYtNP9HkHlY0f5Bc",
	authDomain : "wivart-d15a2.firebaseapp.com",
	databaseURL : "https://wivart-d15a2.firebaseio.com",
	projectId : "wivart-d15a2",
	storageBucket : "gs://wivart-d15a2.appspot.com/",
	messagingSenderId : "559004991790",
	appId : "1:559004991790:web:8ec8b75bc5ef4664",
	clientId:"559004991790-absfnhtq0qthaqhv5oktnmq01lgrm8kl.apps.googleusercontent.com"
});

const key = "timeless";

//var decrypted = CryptoJS.AES.decrypt(encrypted, "Secret Passphrase");

const db = firebase.firestore();

var store = firebase.storage();

function getFileExtension(filename) {
	  return (/[.]/.exec(filename)) ? /[^.]+$/.exec(filename)[0] : '';
}