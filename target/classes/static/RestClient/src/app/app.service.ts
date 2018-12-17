import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LoginComponent} from './components/login/login.component'

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) {

  }

  login(username: string, password: string): Observable<LoginComponent>{
    return this.http.post<LoginComponent>('https://reqres.in/api/login', {
      username: username,
      password: password
    });
  }
}
