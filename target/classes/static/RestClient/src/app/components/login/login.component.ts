import {Component} from '@angular/core';
import {AppService} from '../../app.service';
import {CustomerService} from '../../customer.service';
import {Router} from '@angular/router';
import {UserService} from '../../shared_service/user.service';
import {User} from '../../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public users:User[];
  username:String;
  password:String;

  constructor(private _userService:UserService, private app: AppService, private customer: CustomerService, private router: Router) {
  }

  ngOnInit() {
    this._userService.getUsers().subscribe((users) => {
        console.log(users);
        this.users=users;
    }, (error) => {
      console.log(error);
    })
  }

  tryLogin(user) {
    this.app.login(
      this.username = user.username,
      this.password = user.password
    )
      .subscribe(
        r => {
          for (var i = 0; i < this.users.length; i++){
            if (this.username == this.users[i].username && this.password == this.users[i].password){
              this.router.navigateByUrl('/home');
            }
          }
        },
        r => {
          alert(r.error.error);
        });
  }

}
