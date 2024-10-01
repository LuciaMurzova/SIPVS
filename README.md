# SIPVS
Zadanie 
- tvorba formulára – každá skupina si zvolí sama
- návrh XSD
    - aspoň 3 dátové typy
    - opakujúca sa sekcia
    - aspoň 1 atribút
    - vymyslieť menný priestor - targetNamespace
    - !!!nekomplikovať!!!
- návrh XSL
    - do HTML
    - ak je z reálneho sveta, zabezpečiť maximálnu podobu s papierom
    - readonly bez aktívnych prvkov
- implementácia web aplikácie
    - slúži na zber údajov – vyplnenie formulára
    - zohľadňuje pravidlá v XSD
    - aplikuje spomenuté výhody elektronizácie
        - kontrola, komfort, dopočítavanie, predvypĺňanie, ....
    - aplikácia bude mať 3 tlačidlá
        - Ulož XML - uloží vytvorené XML do súboru
        - Over XML voči XSD - overí uložené XML voči vytvorenému XSD
            - na overenie použije nástroje a triedy jazyka/prostredia
            - výstup validácie zobrazí aj s detailom prípadnej chyby
        - Transformuj XML do HTML - transformuje uložené XML pomocou vytvorenej XSL
            - na transformáciu použije nástroje a triedy jazyka/prostredia
            - výstup uloží do súboru

