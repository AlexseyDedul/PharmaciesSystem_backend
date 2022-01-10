function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var manufacturersApi = Vue.resource('/manufacturer{/id}')

Vue.component('manufacturer-form', {
    props: ['manufacturers', 'manufacturerAttr'],
    data: function() {
        return {
            name: '',
            country: '',
            city: '',
            address: '',
            phoneNumb: '',
            id: ''
        }
    },
    watch: {
        manufacturerAttr: function(newVal, oldVal) {
            this.name = newVal.name;
            this.country = newVal.country;
            this.city = newVal.city;
            this.address = newVal.address;
            this.phoneNumb = newVal.phoneNumb;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
            '<input style="margin:10px;" type="text" placeholder="Name" v-model="name" /><br />' +
            '<input style="margin:10px;" type="text" placeholder="Country" v-model="country" /><br />' +
            '<input style="margin:10px;" type="text" placeholder="City" v-model="city" /><br />' +
            '<input style="margin:10px;" type="text" placeholder="Address" v-model="address" /><br />' +
            '<input style="margin:10px;" type="text" placeholder="Phone Number" v-model="phoneNumb" /><br />' +
            '<input style="margin:10px;" type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var manufacturer = {
                name: this.name,
                country: this.country,
                city: this.city,
                address: this.address,
                phoneNumb: this.phoneNumb
             };

            if (this.id) {
                manufacturersApi.update({id: this.id}, manufacturer).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.manufacturers, data.id);
                        this.manufacturers.splice(index, 1, data);
                        this.name = ''
                        this.country = ''
                        this.city = ''
                        this.address = ''
                        this.phoneNumb = ''
                        this.id = ''
                    })
                )
            } else {
                manufacturersApi.save({}, manufacturer).then(result =>
                    result.json().then(data => {
                        this.manufacturers.push(data);
                        this.name = ''
                        this.country = ''
                        this.city = ''
                        this.address = ''
                        this.phoneNumb = ''
                    })
                )
            }
        }
    }
});

Vue.component('manufacturer-row', {
    props: ['manufacturer', 'editMethod', 'manufacturers'],
    template: '<tr>'+
            '<td>{{manufacturer.name}}</td>'+
            '<td>{{manufacturer.country}}</td>'+
            '<td>{{manufacturer.city}}</td>'+
            '<td>{{manufacturer.address}}</td>'+
            '<td>{{manufacturer.phoneNumb}}</td>'+
            '<td><input type="button" value="Edit" @click="edit" /></td>' +
            '<td><input type="button" value="X" @click="del" /></td>' +
        '</tr>',
    methods: {
            edit: function() {
                this.editMethod(this.manufacturer);
            },
            del: function() {
                manufacturersApi.remove({id: this.manufacturer.id}).then(result => {
                    if (result.ok) {
                        this.manufacturers.splice(this.manufacturers.indexOf(this.manufacturer), 1)
                    }
                })
            }
        }
});

Vue.component('manufacturers-list', {
    props: ['manufacturers'],
    data: function() {
        return {
            manufacturer: null
        }
      },
    template:
    '<div>' +
        '<manufacturer-form :manufacturers="manufacturers" :manufacturerAttr="manufacturer" />' +
        '<table><tr><th>Name</th><th>Country</th><th>City</th><th>Address</th><th>Phone Number</th></tr>' +
            '<manufacturer-row v-for="manufacturer in manufacturers" :key="manufacturer.id" :manufacturer="manufacturer" :editMethod="editMethod" :manufacturers="manufacturers"/>'+
        '</table>'+
    '</div>',
    created: function(){
        manufacturersApi.get().then(result =>
        result.json().then(data =>
                data.forEach(manufacturer => this.manufacturers.push(manufacturer))
            )
        )
    },
    methods: {
        editMethod: function(manufacturer) {
            this.manufacturer = manufacturer;
        }
      }
});

var app = new Vue({
  el: '#app',
  template: '<manufacturers-list :manufacturers="manufacturers" />',
  data: {
    manufacturers: []
  }
});