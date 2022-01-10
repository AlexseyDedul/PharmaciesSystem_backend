function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var diseasesApi = Vue.resource('/disease{/id}')

Vue.component('disease-form', {
    props: ['diseases', 'diseaseAttr'],
    data: function() {
        return {
            name: '',
            treatment: '',
            id: ''
        }
    },
    watch: {
        diseaseAttr: function(newVal, oldVal) {
            this.name = newVal.name;
            this.treatment = newVal.treatment;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
            '<input style="margin:10px;" type="text" placeholder="Name" v-model="name" /><br />' +
            '<input style="margin:10px;" type="text" placeholder="Treatment" v-model="treatment" /><br />' +
            '<input style="margin:10px;" type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var disease = {
                name: this.name,
                treatment: this.treatment
             };

            if (this.id) {
                diseasesApi.update({id: this.id}, disease).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.diseases, data.id);
                        this.diseases.splice(index, 1, data);
                        this.name = ''
                        this.treatment = ''
                        this.id = ''
                    })
                )
            } else {
                diseasesApi.save({}, disease).then(result =>
                    result.json().then(data => {
                        this.diseases.push(data);
                        this.name = ''
                        this.treatment = ''
                    })
                )
            }
        }
    }
});

Vue.component('disease-row', {
    props: ['disease', 'editMethod', 'diseases'],
    template: '<tr>'+
            '<td>{{disease.name}}</td>'+
            '<td>{{disease.treatment}}</td>'+
            '<td><input type="button" value="Edit" @click="edit" /></td>' +
            '<td><input type="button" value="X" @click="del" /></td>' +
        '</tr>',
    methods: {
            edit: function() {
                this.editMethod(this.disease);
            },
            del: function() {
                diseasesApi.remove({id: this.disease.id}).then(result => {
                    if (result.ok) {
                        this.diseases.splice(this.diseases.indexOf(this.disease), 1)
                    }
                })
            }
        }
});

Vue.component('diseases-list', {
    props: ['diseases'],
    data: function() {
        return {
            disease: null
        }
      },
    template:
    '<div>' +
        '<disease-form :diseases="diseases" :diseaseAttr="disease" />' +
        '<table><tr><th>Name</th><th>Treatment</th></tr>' +
            '<disease-row v-for="disease in diseases" :key="disease.id" :disease="disease" :editMethod="editMethod" :diseases="diseases"/>'+
        '</table>'+
    '</div>',
    created: function(){
        diseasesApi.get().then(result =>
        result.json().then(data =>
                data.forEach(disease => this.diseases.push(disease))
            )
        )
    },
    methods: {
        editMethod: function(disease) {
            this.disease = disease;
        }
      }
});

var app = new Vue({
  el: '#app',
  template: '<diseases-list :diseases="diseases" />',
  data: {
    diseases: []
  }
});