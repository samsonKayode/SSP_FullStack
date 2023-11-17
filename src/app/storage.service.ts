import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class StorageService {

    constructor() { }

    public saveData(key: string, value: string) {
        localStorage.setItem(key, value);
    }

    public getData(key: string) {
        let data = localStorage.getItem(key) || "";
        return data;
    }

    public clearData() {
        localStorage.clear();
    }

}