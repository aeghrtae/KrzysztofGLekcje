import os

nazwaPliku = "dane.txt"

while True:
    print("\n--- MENU ---")
    print("1. Odczyt danych z pliku")
    print("2. Dopisywanie danych")
    print("3. Nadpisywanie pliku")
    print("4. Czyszczenie pliku")
    print("5. Wyszukiwanie tekstu")
    print("6. Wyświetlanie numerowanych linii")
    print("7. Zakończenie programu")
    
    wybor = input("Wybierz opcję (1-7): ")
    
   
    if wybor == '1':
        if os.path.exists(nazwaPliku):
            with open(nazwaPliku, "r", encoding="utf-8") as f:
                print("\nZawartosc pliku:\n" + f.read())
        else:
            print("Plik not exist")
            
   
    elif wybor == '2':
        text = input("Wpisz tekst do dopisania: ")
        with open(nazwaPliku, "a", encoding="utf-8") as f:
            f.write(text + "\n")
        print("Dopisano tekst.")
        
  
    elif wybor == '3':
        text = input("Wpisz nowy tekst: ")
        with open(nazwaPliku, "w", encoding="utf-8") as f:
            f.write(text + "\n")
        print("Zapisano nowy tekst.")
        
   
    elif wybor == '4':
        open(nazwaPliku, "w").close()
        print("Wyczyszczono zawartość pliku.")
        
   
    elif wybor == '5':
        if os.path.exists(nazwaPliku):
            wyszuk = input("Podaj frazę do wyszukania: ")
            with open(nazwaPliku, "r", encoding="utf-8") as f:
                zawartosc = f.read()
                nrw = zawartosc.count(wyszuk)
            print(f"Fraza występuje {nrw} razy.")
        else:
            print("Fraza nie istnieji")
            
   
    elif wybor == '6':
        if os.path.exists(nazwaPliku):
            with open(nazwaPliku, "r", encoding="utf-8") as f:
                for nr, linia in enumerate(f, 1):
                    print(f"{nr}: {linia.strip()}")
        else:
            print("Plik nie istnieje")
            
   
    elif wybor == '7':
        print("Koniec programu.")
        break
        
    else:
        print("Nieprawidłowy wybór, spróbuj ponownie.")