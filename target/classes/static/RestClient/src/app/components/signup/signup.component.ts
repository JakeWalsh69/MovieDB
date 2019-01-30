import { Component, OnInit } from '@angular/core';
import { User } from "../../user";
import { UserService } from '../../shared_service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public users:User[];
  user:User;
  isValid:boolean;
  lengthValid:boolean;

  constructor(private _userService:UserService, private _router:Router) { }

  ngOnInit() {
    this._userService.getUsers().subscribe((users) => {
      console.log(users);
      this.users=users;
    }, (error) => {
    console.log(error);
    })
    this.user=this._userService.getter();
  }

  processForm(){
    if(this.user.id==undefined){
      this.isValid = true;
        for (var i = 0; i < this.users.length; i++){
          if (this.user.username == this.users[i].username){
            this.isValid = false;
            this.lengthValid = true;
          }
        }
        if(this.isValid == true && this.user.username.length >= 4 && this.user.username.length <= 15){
          this._userService.createUser(this.user).subscribe((user)=>{
            console.log(user);
            this.lengthValid = true;
            this.isValid = true;
            this._router.navigate(['/login']);
          },(error)=>{
            console.log(error);
          });
        }
        else if (this.isValid == true && this.user.username.length < 4 || this.user.username.length > 15){
            this.lengthValid = false;
        }
      }
        else{
          this._router.navigate(['/signup']);
      }
  }

}
