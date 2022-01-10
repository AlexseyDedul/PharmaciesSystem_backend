function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var pharmaciesApi = Vue.resource('/pharmacies{/id}')

Vue.component('pharmacy-form', {
    props: ['pharmacies', 'pharmacyAttr'],
    data: function() {
        return {
            name: '',
            stateNumber: '',
            country: '',
            address: '',
            id: ''
        }
    },
    watch: {
        pharmacyAttr: function(newVal, oldVal) {
            this.name = newVal.name;
            this.stateNumber = newVal.stateNumber;
            this.country = newVal.country;
            this.address = newVal.address;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
            '<input style="margin:10px;" type="text" placeholder="Name" v-model="name" /><br />' +
            '<input style="margin:10px;" type="text" placeholder="State Number" v-model="stateNumber" /><br />' +
            '<input style="margin:10px;" type="text" placeholder="Country" v-model="country" /><br />' +
            '<input style="margin:10px;" type="text" placeholder="Address" v-model="address" /><br />' +
            '<input style="margin:10px;" type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var pharmacy = {
                name: this.name,
                stateNumber: this.stateNumber,
                country: this.country,
                address: this.address
             };

            if (this.id) {
                pharmaciesApi.update({id: this.id}, pharmacy).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.pharmacies, data.id);
                        this.pharmacies.splice(index, 1, data);
                        this.name = ''
                        this.stateNumber = ''
                        this.country = ''
                        this.address = ''
                        this.id = ''
                    })
                )
            } else {
                pharmaciesApi.save({}, pharmacy).then(result =>
                    result.json().then(data => {
                        this.pharmacies.push(data);
                        this.name = ''
                        this.stateNumber = ''
                        this.country = ''
                        this.address = ''
                    })
                )
            }
        }
    }
});

Vue.component('pharmacy-row', {
    props: ['pharmacy', 'editMethod', 'pharmacies'],
    template: '<tr>'+
            '<td>{{pharmacy.name}}</td>'+
            '<td>{{pharmacy.stateNumber}}</td>'+
            '<td>{{pharmacy.country}}</td>'+
            '<td>{{pharmacy.address}}</td>'+
            '<td><input type="button" value="Edit" @click="edit" /></td>' +
            '<td><input type="button" value="X" @click="del" /></td>' +
        '</tr>',
    methods: {
            edit: function() {
                this.editMethod(this.pharmacy);
            },
            del: function() {
                pharmaciesApi.remove({id: this.pharmacy.id}).then(result => {
                    if (result.ok) {
                        this.pharmacies.splice(this.pharmacies.indexOf(this.pharmacy), 1)
                    }
                })
            }
        }
});

Vue.component('pharmacies-list', {
    props: ['pharmacies'],
    data: function() {
        return {
            pharmacy: null
        }
      },
    template:
    '<div>' +
        '<pharmacy-form :pharmacies="pharmacies" :pharmacyAttr="pharmacy" />' +
        '<table><tr><th>Name</th><th>State Number</th><th>Country</th><th>Address</th></tr>' +
            '<pharmacy-row v-for="pharmacy in pharmacies" :key="pharmacy.id" :pharmacy="pharmacy" :editMethod="editMethod" :pharmacies="pharmacies"/>'+
        '</table>'+
    '</div>',
    created: function(){
        pharmaciesApi.get().then(result =>
        result.json().then(data =>
                data.forEach(pharmacy => this.pharmacies.push(pharmacy))
            )
        )
    },
    methods: {
        editMethod: function(pharmacy) {
            this.pharmacy = pharmacy;
        }
      }
});

var app = new Vue({
  el: '#app',
  template: '<pharmacies-list :pharmacies="pharmacies" />',
  data: {
    pharmacies: []
  }
});