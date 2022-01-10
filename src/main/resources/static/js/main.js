function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var userApi = Vue.resource('/user{/id}')

Vue.component('user-form', {
    props: ['users', 'userAttr'],
    data: function() {
        return {
            surname: '',
            name: '',
            phone: '',
            email: '',
            position: '',
            pharmacy: '',
            login: '',
            password: '',
            id: ''
        }
    },
    watch: {
        userAttr: function(newVal, oldVal) {
            this.surname = newVal.surname;
            this.name = newVal.name;
            this.phone = newVal.phone;
            this.email = newVal.email;
            this.position = newVal.position;
            this.pharmacy = newVal.pharmacy;
            this.login = newVal.login;
            this.password = newVal.password;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
            '<input style="margin:10px;" type="text" placeholder="Surname" v-model="surname" /><br />' +
            '<input style="margin:10px;" type="text" placeholder="Name" v-model="name" /><br />' +
            '<input style="margin:10px;" type="text" placeholder="Phone" v-model="phone" /><br />' +
            '<input style="margin:10px;" type="text" placeholder="Email" v-model="email" /><br />' +
            '<input style="margin:10px;" type="text" placeholder="Position" v-model="position" /><br />' +
            '<input style="margin:10px;" type="text" placeholder="Pharmacy" v-model="pharmacy" /><br />' +
            '<select>'+
               '<option v-for="pharmacy in pharmacies" :key="pharmacy.id" :pharmacy="pharmacy">{{pharmacy.name}}</option>'+
             '</select>'+
            '<input style="margin:10px;" type="text" placeholder="Login" v-model="login" /><br />' +
            '<input style="margin:10px;" type="text" placeholder="Password" v-model="password" /><br />' +
            '<input style="margin:10px;" type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var user = {
                surname: this.surname,
                name: this.name,
                phone: this.phone,
                email: this.email,
                position: this.position,
                pharmacy: this.pharmacy,
                login: this.login,
                password: this.password
             };

            if (this.id) {
                userApi.update({id: this.id}, user).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.users, data.id);
                        this.users.splice(index, 1, data);
                        this.surname = ''
                        this.name = ''
                        this.phone = ''
                        this.email = ''
                        this.pharmacy = ''
                        this.login = ''
                        this.password = ''
                        this.id = ''
                    })
                )
            } else {
                userApi.save({}, user).then(result =>
                    result.json().then(data => {
                        this.users.push(data);
                        this.surname = ''
                        this.name = ''
                        this.phone = ''
                        this.email = ''
                        this.position = ''
                        this.pharmacy = ''
                        this.login = ''
                        this.password = ''
                    })
                )
            }
        }
    }
});

Vue.component('user-row', {
    props: ['user', 'editMethod', 'users'],
    template: '<tr>'+
            '<td>{{user.surname}}</td>'+
            '<td>{{user.name}}</td>'+
            '<td>{{user.phone}}</td>'+
            '<td>{{user.email}}</td>'+
            '<td>{{user.position}}</td>'+
            '<td>{{user.pharmacy.name}}</td>'+
            '<td>{{user.login}}</td>'+
            '<td>{{user.password}}</td>'+
            '<td><input type="button" value="Edit" @click="edit" /></td>' +
            '<td><input type="button" value="X" @click="del" /></td>' +
        '</tr>',
    methods: {
            edit: function() {
                this.editMethod(this.user);
            },
            del: function() {
                userApi.remove({id: this.user.id}).then(result => {
                    if (result.ok) {
                        this.users.splice(this.users.indexOf(this.user), 1)
                    }
                })
            }
        }
});

Vue.component('users-list', {
    props: ['users'],
    data: function() {
        return {
            user: null
        }
      },
    template:
    '<div>' +
        '<user-form :users="users" :userAttr="user" />' +
        '<table><tr><th>surname</th><th>Name</th><th>phone</th><th>email</th><th>position</th><th>pharmacy</th><th>login</th><th>password</th></tr>' +
            '<user-row v-for="user in users" :key="user.id" :user="user" :editMethod="editMethod" :users="users"/>'+
        '</table>'+
    '</div>',
    created: function(){
        userApi.get().then(result =>
        result.json().then(data =>
                data.forEach(user => this.users.push(user))
            )
        )
    },
    methods: {
        editMethod: function(user) {
            this.user = user;
        }
      }
});

var app = new Vue({
  el: '#app',
  template: '<users-list :users="users" />',
  data: {
    users: []
  }
});